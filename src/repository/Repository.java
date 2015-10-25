package repository;

import interfaces.*;
import model.ProgramState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucian on 10/13/2015.
 */
public class Repository implements IRepository {

    private List<ProgramState> mProgramStateList;

    public Repository() {
        mProgramStateList = new ArrayList<>();
    }

    public void createProgram(IStack<IStatement> mExecutionStack, IDictionary<String, Integer> myDictionary, IList<String> mOutput, IStatement mInitialProgram) {
        mProgramStateList.add(new ProgramState(mExecutionStack, myDictionary, mOutput, mInitialProgram));
    }

    @Override
    public ProgramState getCurrentState() {
        return mProgramStateList.get(mProgramStateList.size() - 1);
    }

    @Override
    public void saveProgramState() {

    }
}
