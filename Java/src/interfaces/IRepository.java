package interfaces;

import Exceptions.InvalidPositionException;
import model.ProgramState;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lucian on 10/13/2015.
 */
public interface IRepository {

    ProgramState getCurrentState() throws InvalidPositionException;

    /**
     * Set the list of program states
     * @param list the list
     */
    void setProgramStateList(List<ProgramState> list);

    /**
     * Get the program state list
     * @return The list
     */
    List<ProgramState> getProgramStateList();

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

    /**
     * Return the program state with the most elements
     * @return The program state
     */
    ProgramState getBiggerProgramState();

    /**
     * Remove all the program states that are complete
     */
    void removeCompleteProgramState();

}
