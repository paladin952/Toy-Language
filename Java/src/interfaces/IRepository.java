package interfaces;

import Exceptions.InvalidPositionException;
import model.ProgramState;

import java.io.Serializable;

/**
 * Created by Lucian on 10/13/2015.
 */
public interface IRepository {

    /**
     * @return The current Program's state
     */
    ProgramState getCurrentState() throws InvalidPositionException;

    /**
     * Creates a new program
     *
     * @param mExecutionStack The execution stack of the program
     * @param myDictionary    The table of symbols for program
     * @param mOutput         The output list of the program
     * @param mInitialProgram The initial statement
     */
    void createProgram(IStack<IStatement> mExecutionStack, IDictionary<String, Integer> myDictionary, IList<String> mOutput, IHeap<Integer, Integer>heap, IStatement mInitialProgram);

    /**
     * Serialize the repository in text file
     */
    void serialize();

    /**
     * deserialize from file
     */
    void deSerialize();

    /**
     * Save the state of the repo in file
     */
    void saveStateInFile();

}
