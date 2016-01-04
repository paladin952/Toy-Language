package controller;

import Exceptions.*;
import interfaces.*;
import model.Collections.MyHeap;
import model.Collections.WrapperDictionary;
import model.Collections.WrapperList;
import model.Collections.WrapperStack;
import model.Statements.WriteHeapStatement;
import model.ProgramState;
import model.Statements.*;
import utils.Constants;

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
     * @param programState The program
     * @throws StatementExecutionException
     */
    private void oneStep(ProgramState programState) throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        IStack<IStatement> myStack = programState.getExecutionStack();
        IHeap<Integer, Integer>heap = programState.getHeap();
        IDictionary<String, Integer> myDictionary = programState.getMyDictionary();
        IList<String> output = programState.getOutput();
        if (myStack.isEmpty())
            throw new StatementExecutionException();
        IStatement statement = myStack.pop();

        if (statement instanceof CompoundStatement) {
            CompoundStatement compoundStatement1 = (CompoundStatement) statement;
            myStack.push(compoundStatement1.getSecondStatement());
            myStack.push(compoundStatement1.getFirstStatement());
            return;
        }

        if (statement instanceof AssignStatement) {
            AssignStatement assignStatement = (AssignStatement) statement;
            Expression expression = assignStatement.getExpression();
            String id = assignStatement.getVariableName();

            int val = expression.eval(myDictionary, heap);
            //insert or update
            myDictionary.put(id, val);
            return;
        }

        if (statement instanceof PrintStatement) {

            PrintStatement printStatement = (PrintStatement) statement;
            Expression expr = printStatement.getExpression();
            output.add(String.valueOf(expr.eval(myDictionary, heap)));
            return;
        }

        if (statement instanceof IfStatement) {
            IfStatement ifStatement = (IfStatement) statement;
            if (ifStatement.getExpression().eval(myDictionary, heap) != 0) {
                myStack.push(ifStatement.getThenStatement());
            } else {
                if (ifStatement.getElseStatement() != null) {
                    myStack.push(ifStatement.getElseStatement());
                }
            }
            return;
        }

        if (statement instanceof WhileStatement) {
            WhileStatement whileStatement = (WhileStatement) statement;
            IStack<IStatement> secondStack = new WrapperStack<>();
            ProgramState secondProgramState = new ProgramState(secondStack, myDictionary, output, heap, whileStatement.getStatement());
            while (whileStatement.getExpression().eval(myDictionary, heap) != 0) {
                runAllSteps(secondProgramState);
                secondStack.push(whileStatement.getStatement());
            }
            return;
        }

        if (statement instanceof SkipStatement) {
            return;
        }

        if (statement instanceof SwitchStatement) {
            SwitchStatement switchStatement = (SwitchStatement) statement;
            Expression expression = switchStatement.getExpression();
            if (switchStatement.getCase1().eval(myDictionary, heap) == expression.eval(myDictionary, heap)) {
                myStack.push(switchStatement.getStatementCase1());
            }
            if (switchStatement.getCase2().eval(myDictionary, heap) == expression.eval(myDictionary, heap)) {
                myStack.push(switchStatement.getStatementCase2());
            }
            myStack.push(switchStatement.getStatementDefault());
        }

        if(statement instanceof HeapAllocation){
            HeapAllocation heapAllocation = (HeapAllocation)statement;
            int pointer = heap.size();
            heap.put(pointer, heapAllocation.getExpression().eval(myDictionary, heap));
            myDictionary.put(heapAllocation.getVariableName(), pointer);
        }

        if(statement instanceof WriteHeapStatement){
            WriteHeapStatement writeHeapStatement = (WriteHeapStatement)statement;
            heap.put(myDictionary.lookUp(writeHeapStatement.getVariableName()), writeHeapStatement.getExpression().eval(myDictionary, heap));
        }
    }

    /**
     * Run all program
     *
     * @throws StatementExecutionException
     */
    public void runAllSteps() throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        ProgramState programState = repository.getCurrentState();
        while (!programState.getExecutionStack().isEmpty()) {
            oneStep(programState);
            if (PRINT_FLAG == Constants.PRINT_CONSOLE) {
                printListener.print(programState.toString());
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
    private void runAllSteps(ProgramState programState) throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        while (!programState.getExecutionStack().isEmpty()) {
            oneStep(programState);
        }
    }

    /**
     * Run step by step
     *
     * @throws StatementExecutionException
     */
    public void runOneStep() throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        ProgramState programState = repository.getCurrentState();
        if (programState.getExecutionStack().size() > 0) {
            oneStep(programState);
            if (PRINT_FLAG == Constants.PRINT_CONSOLE) {
                printListener.print(programState.toString());
            } else if (PRINT_FLAG == Constants.PRINT_IN_FILE) {
                repository.saveStateInFile();
            }
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