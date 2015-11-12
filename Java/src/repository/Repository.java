package repository;

import Exceptions.InvalidPositionException;
import interfaces.*;
import model.ProgramState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucian on 10/13/2015.
 */
public class Repository implements IRepository {

    /**
     * The list of current program states
     * For now only one item will be there
     */
    private List<ProgramState> programStateList;

    /**
     * The constructor
     */
    public Repository() {
        programStateList = new ArrayList<>();
    }

    public void createProgram(IStack<IStatement> mExecutionStack, IDictionary<String, Integer> myDictionary, IList<String> mOutput, IStatement mInitialProgram) {
        programStateList.add(new ProgramState(mExecutionStack, myDictionary, mOutput, mInitialProgram));
    }

    /**
     * Get the latest program state
     * @return The ProgramState
     */
    @Override
    public ProgramState getCurrentState() throws InvalidPositionException{
        if(programStateList.size()>0){
            return programStateList.get(programStateList.size() - 1);
        }
        throw new InvalidPositionException();
    }

}
