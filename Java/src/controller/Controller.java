package controller;

import Exceptions.*;
import interfaces.*;
import model.Collections.MyHeap;
import model.Collections.WrapperDictionary;
import model.Collections.WrapperList;
import model.Collections.WrapperStack;
import model.ProgramState;
import threads.ForkThread;
import utils.Constants;

import java.util.Collections;
import java.util.List;

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
        repository.createProgram(new WrapperStack<>(), new WrapperDictionary<>(), new WrapperList<>(), new MyHeap(), initialStatement);
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
     * Run the program in debug mode, one step at a time
     *
     * @throws StatementExecutionException
     */
    private void oneStepForAll() throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {

        List<ProgramState> list = repository.getProgramStateList();
        Collections.synchronizedList(list);
        repository.removeCompleteProgramState();
        ProgramState isFork;

        for (int i = 0; i < list.size(); i++) {
            isFork = list.get(i).oneStep(list.get(i));

            if (isFork != null) {
                Runnable runnable = new ForkThread(isFork, printListener);
                new Thread(runnable).start();
            }

            if (PRINT_FLAG == Constants.PRINT_CONSOLE) {
                System.out.println("Id=1");
                printListener.print(list.get(i).toString());
            } else if (PRINT_FLAG == Constants.PRINT_IN_FILE) {
                repository.saveStateInFile();
            }
        }
    }

    /**
     * Run all program
     *
     * @throws StatementExecutionException
     */
    public void runAllSteps() throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        while (!repository.getBiggerProgramState().getExecutionStack().isEmpty()) {
            oneStepForAll();
        }
    }

    /**
     * Run step by step
     *
     * @throws StatementExecutionException
     */
    public void runOneStep() throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        if (repository.getBiggerProgramState().getExecutionStack().size() > 0) {
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
     * Deserializing the program state
     */
    public void deSerialize() {
        repository.deSerialize();
    }

    public void saveInFile() {
        repository.saveStateInFile();
    }
}
