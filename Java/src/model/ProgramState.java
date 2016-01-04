package model;

import Exceptions.*;
import interfaces.*;
import model.Collections.WrapperStack;
import model.Statements.WhileStatement;

/**
 * Created by Lucian on 10/19/2015.
 */
public class ProgramState implements java.io.Serializable {

    /**
     * The execution stack containing statements
     */
    private IStack<IStatement> executionStack;

    /**
     * The table of variables as a hash map
     */
    private IDictionary<String, Integer> myDictionary;

    /**
     * List of output
     * The messages are coming from print statement
     */
    private IList<String> output;

    /**
     * The heap
     */
    private IHeap<Integer, Integer> heap;
    /**
     * A copy of the original program
     * I don't need to serialize it
     */
    private transient IStatement originalProgram;

    public ProgramState(IStack<IStatement> executionStack, IDictionary<String, Integer> myDictionary, IList<String> output, IHeap<Integer, Integer> heap, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.myDictionary = myDictionary;
        this.output = output;
        this.originalProgram = originalProgram;
        this.executionStack.push(originalProgram);
        this.heap = heap;
    }

    /**
     * String representation
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Execution stack: \n");
        result.append(executionStack.toString());

        result.append("HEAP: \n");
        result.append(heap.toString());

        result.append("My dictionary: \n");
        result.append("\n");
        result.append(myDictionary.toString());

        result.append("Output: \n");
        result.append(output.toString());
        return result.toString();
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
    public boolean isComplete() {
        return executionStack.isEmpty();
    }

    /**
     * Run the program in debug mode, one step at a time
     *
     * @param programState The program
     * @throws StatementExecutionException
     */
    public ProgramState oneStep(ProgramState programState) throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        IStack<IStatement> myStack = getExecutionStack();
        if (myStack.isEmpty())
            throw new StatementExecutionException();
        IStatement statement = myStack.pop();
        return statement.execute(programState);


    }

    /**
     * Getters and setters
     */

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(IStack<IStatement> mExecutionStack) {
        this.executionStack = mExecutionStack;
    }

    public IDictionary<String, Integer> getMyDictionary() {
        return myDictionary;
    }

    public void setMyDictionary(IDictionary<String, Integer> myDictionary) {
        this.myDictionary = myDictionary;
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
