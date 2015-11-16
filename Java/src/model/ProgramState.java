package model;

import interfaces.IDictionary;
import interfaces.IList;
import interfaces.IStack;
import interfaces.IStatement;

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
     * A copy of the original program
     * I don't need to serialize it
     */
    private transient IStatement originalProgram;

    public ProgramState(IStack<IStatement> executionStack, IDictionary<String, Integer> myDictionary, IList<String> output, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.myDictionary = myDictionary;
        this.output = output;
        this.originalProgram = originalProgram;
        this.executionStack.push(originalProgram);
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

        result.append("My dictionary: \n");
        result.append("\n");
        result.append(myDictionary.toString());

        result.append("Output: \n");
        result.append(output.toString());
        return result.toString();
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
