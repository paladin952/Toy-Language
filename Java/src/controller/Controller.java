package controller;

import Exceptions.*;
import interfaces.*;
import model.Collections.MyHeap;
import model.Collections.WrapperDictionary;
import model.Collections.WrapperList;
import model.Collections.WrapperStack;
import model.ProgramState;
import utils.Constants;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by Lucian on 10/11/2015.
 */
public class Controller {

    /**
     * Boolean: print current program state if it is true
     */
    public static int PRINT_FLAG = Constants.PRINT_CONSOLE;

    /**
     * Repository object
     */
    private IRepository repository;

    /**
     * The constructor
     *
     * @param repository The repository
     */
    public Controller(IRepository repository) {
        this.repository = repository;

    }

    /**
     * Interface for printing on screen from ui
     */
    public interface PrintState {
        void print(String message);
    }

    /**
     * Listener for print
     */
    private PrintState printListener;

    /**
     * Creating a new program based on initial statement
     *
     * @param initialStatement initial IStatement
     */
    public void createProgram(IStatement initialStatement) {
        repository.createProgram(new WrapperStack<>(), new Stack<>(), new WrapperList<>(), new MyHeap(), initialStatement);
    }

    /**
     * set listener for printing in ui
     *
     * @param listener The listener
     */
    public void setListener(PrintState listener) {
        printListener = listener;
    }

    /**
     * Removes the completed program states
     *
     * @param inPrgList The program state lists
     * @return The program state list
     */
    public java.util.List<ProgramState> removeCompletedPrg(java.util.List<ProgramState> inPrgList) {
        return inPrgList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    /**
     * Run the program in debug mode, one step at a time
     *
     * @throws StatementExecutionException
     */
    private void oneStepForAll() throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException, InterruptedException {

        List<ProgramState> programStatesList = repository.getProgramStateList();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        java.util.List<Callable<ProgramState>> callList = programStatesList.stream()
                .map(p -> (Callable<ProgramState>) p::oneStep)
                .collect(Collectors.toList());

        java.util.List<ProgramState> newProgramState = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        programStatesList.forEach(p -> {
            if (!newProgramState.stream().anyMatch(s -> s.getStateId() == p.getStateId()))
                newProgramState.add(p);
        });

        repository.setProgramStateList(programStatesList);

        executor.shutdown();

        if (PRINT_FLAG == Constants.PRINT_IN_FILE) {
            newProgramState.forEach(p -> saveInFile(p.toString()));
        } else if (PRINT_FLAG == Constants.PRINT_CONSOLE) {
            newProgramState.forEach(p -> printListener.print(p.toString()));
        }
    }

    /**
     * Run all program
     *
     * @throws StatementExecutionException
     */
    public void runAllSteps() throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException, InterruptedException {
        while (true) {
            java.util.List<ProgramState> prgList = removeCompletedPrg(repository.getProgramStateList());
            if (prgList.size() == 0) {
                return;
            } else {
                oneStepForAll();
            }
        }
    }

    /**
     * Run step by step
     *
     * @throws StatementExecutionException
     */
    public void runOneStep() throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException, InterruptedException {
        java.util.List<ProgramState> prgList = removeCompletedPrg(repository.getProgramStateList());
        if (prgList.size() == 0) {
            return;
        } else {
            oneStepForAll();
        }
    }

    /**
     * serializing the program state
     */
    public void serialize() {
        repository.serialize();
    }

    /**
     * Deserialisation the program state
     */
    public void deSerialize() {
        repository.deSerialize();
    }

    public void saveInFile(String message) {
        repository.saveStateInFile(message);
    }
}
