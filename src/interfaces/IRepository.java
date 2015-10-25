package interfaces;

import model.ProgramState;

/**
 * Created by Lucian on 10/13/2015.
 */
public interface IRepository {

    ProgramState getCurrentState();

    void saveProgramState();

    void createProgram(IStack<IStatement> mExecutionStack, IDictionary<String, Integer> myDictionary, IList<String> mOutput, IStatement mInitialProgram);


}
