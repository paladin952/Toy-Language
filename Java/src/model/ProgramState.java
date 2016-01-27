package model;

import Exceptions.*;
import interfaces.*;
import javafx.util.Pair;
import model.Collections.WrapperDictionary;
import model.Collections.WrapperStack;
import model.Statements.WhileStatement;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Lucian on 10/19/2015.
 */
public class ProgramState implements java.io.Serializable {

    /**
     * The execution stack containing statements
     */
    private IStack<IStatement> executionStack;

    /**
     * List of output
     * The messages are coming from print statement
     */
    private IList<String> output;

    /**
     * The state id
     */
    private int stateId;

    /**
     * The global state id
     */
    private static int globalStateId = 0;

    private Stack<IDictionary<String, Integer>> symbolicTableStack;

    private Map<String, Pair<List<String>, IStatement>> proceduresTable;



    /**
     * The heap
     */
    private IHeap<Integer, Integer> heap;
    /**
     * A copy of the original program
     * I don't need to serialize it
     */
    private transient IStatement originalProgram;


    public ProgramState(IStack<IStatement> executionStack, Stack<IDictionary<String, Integer>> symbolicTableStack, IList<String> output, IHeap<Integer, Integer> heap, Map<String, Pair<List<String>, IStatement>> proceduresTable, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.symbolicTableStack = symbolicTableStack;
        this.output = output;
        this.originalProgram = originalProgram;
        this.executionStack.push(originalProgram);
        this.heap = heap;
        this.stateId = globalStateId ++;
        this.proceduresTable = proceduresTable;
    }


    /**
     * String representation
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Id: " + stateId + "\n");
        result.append("Execution stack: \n");
        result.append(executionStack.toString());

        result.append("HEAP: \n");
        result.append(heap.toString());

//        result.append("My dictionary: \n");
//        result.append("\n");
//        result.append(myDictionary.toString());

        result.append("Output: \n");
        result.append(output.toString());
        return result.toString();
    }

    public Map<String, Pair<List<String>, IStatement>> getProceduresTable() {
        return proceduresTable;
    }

    /**
     * @return The heap object
     */
    public IHeap<Integer, Integer> getHeap() {
        return heap;
    }

    /**
     * Set the heap
     *
     * @param heap The heap
     */
    public void setHeap(IHeap<Integer, Integer> heap) {
        this.heap = heap;
    }

    /**
     * @return True if executionStack is complete, false otherwise
     */
    public boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    /**
     * Run the program in debug mode, one step at a time
     *
     * @throws StatementExecutionException
     */
    public ProgramState oneStep() throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        IStack<IStatement> myStack = getExecutionStack();
        if (myStack.isEmpty())
            throw new StatementExecutionException();
        IStatement statement = myStack.pop();
        return statement.execute(this);
    }

    /**
     * Getters and setters
     */

    public int getStateId(){
        return stateId;
    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(IStack<IStatement> mExecutionStack) {
        this.executionStack = mExecutionStack;
    }

    public IDictionary<String, Integer> getMyDictionary() {
        return symbolicTableStack.peek();
    }

    public void setMyStackDictionary(Stack<IDictionary<String, Integer>> symbolicTableStack) {
        this.symbolicTableStack = symbolicTableStack;
    }

    public Stack<IDictionary<String, Integer>> getStackDictionary(){
        return symbolicTableStack;
    }

    public IList<String> getOutput() {
        return output;
    }

    public void setOutput(IList<String> output) {
        this.output = output;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }
}
