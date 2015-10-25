package model;

import interfaces.IDictionary;
import interfaces.IList;
import interfaces.IStack;
import interfaces.IStatement;
import model.Collections.MyDictionary;
import model.Collections.MyList;
import model.Collections.MyStack;

import java.util.List;

/**
 * Created by Lucian on 10/19/2015.
 */
public class ProgramState {

    private IStack<IStatement> mExecutionStack;
    private IDictionary<String, Integer> myDictionary;
    private IList<String> output;
    private IStatement mOriginalProgram;

    public ProgramState(IStack<IStatement> mExecutionStack, IDictionary<String, Integer> myDictionary, IList<String> output, IStatement mOriginalProgram) {
        this.mExecutionStack = mExecutionStack;
        this.myDictionary = myDictionary;
        this.output = output;
        this.mOriginalProgram = mOriginalProgram;
        this.mExecutionStack.push(mOriginalProgram);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Execution stack: \n");
        result.append(mExecutionStack.toString());

        result.append("My dictionary: \n");
        result.append("\n");
        result.append(myDictionary.toString());

        result.append("Output: \n");
        result.append(output.toString());
        return result.toString() ;
    }

    public IStack<IStatement> getExecutionStack() {
        return mExecutionStack;
    }

    public void setExecutionStack(IStack<IStatement> mExecutionStack) {
        this.mExecutionStack = mExecutionStack;
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

    public IStatement getmOriginalProgram() {
        return mOriginalProgram;
    }

    public void setmOriginalProgram(IStatement mOriginalProgram) {
        this.mOriginalProgram = mOriginalProgram;
    }
}
