package interfaces;

import Exceptions.InvalidPositionException;
import model.ProgramState;

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
    void createProgram(IStack<IStatement> mExecutionStack, IDictionary<String, Integer> myDictionary, IList<String> mOutput, IStatement mInitialProgram);


}
