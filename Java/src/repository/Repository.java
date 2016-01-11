package repository;

import Exceptions.InvalidPositionException;
import interfaces.*;
import model.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucian on 10/13/2015.
 */
public class Repository implements IRepository {

    public static final String PATH_TO_SERIALIZATION_FILE = "src/file.ser";
    public static final String PATH_TO_SAVE_STATE_FILE = "src/saveState.txt";


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

    public ProgramState getCurrentState() throws InvalidPositionException {
        if (programStateList.size() > 0) {
            return programStateList.get(programStateList.size() - 1);
        }
        throw new InvalidPositionException();
    }

    public void createProgram(IStack<IStatement> mExecutionStack, IDictionary<String, Integer> myDictionary, IList<String> mOutput, IHeap<Integer, Integer> heap, IStatement mInitialProgram) {
        programStateList.add(new ProgramState(mExecutionStack, myDictionary, mOutput, heap, mInitialProgram));
    }

    @Override
    public void serialize() {
        try {
            FileOutputStream fileOut = new FileOutputStream(PATH_TO_SERIALIZATION_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(programStateList.get(programStateList.size() - 1));
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Override
    public void deSerialize() {
        try {
            FileInputStream fileIn = new FileInputStream(PATH_TO_SERIALIZATION_FILE);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ProgramState serializedProgramState = (ProgramState) in.readObject();
            in.close();
            fileIn.close();

            /**
             * Replacing old program state
             */
            if (programStateList.size() > 0) {
                programStateList.remove(programStateList.size() - 1);
            }
            programStateList.add(serializedProgramState);
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Program state class not found");
            c.printStackTrace();
            return;
        }
    }

    @Override
    public void saveStateInFile(String message) {
        PrintWriter writer = null;
        ProgramState programState = programStateList.get(programStateList.size() - 1);
        try {
            writer = new PrintWriter(PATH_TO_SAVE_STATE_FILE, "UTF-8");
//            writer.println("STACK:");
//            writer.println(programState.getExecutionStack().toString());
//            writer.println("Symbolic Table");
//            writer.println(programState.getMyDictionary().toString());
//            writer.println("Output");
//            writer.println(programState.getOutput().toString());
            writer.println(message);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProgramState getBiggerProgramState() {
        int max = -1;
        ProgramState result = null;
        for(ProgramState states:programStateList){
            if(states.getExecutionStack().size()>max){
                max = states.getExecutionStack().size();
                result = states;
            }
        }
        return result;
    }

    @Override
    public void removeCompleteProgramState() {
        List<ProgramState> copyOfList = new ArrayList<ProgramState>(programStateList);
        for(ProgramState state:copyOfList){
            if (state.isNotCompleted()){
                programStateList.remove(state);
            }
        }
    }

    @Override
    public void setProgramStateList(List<ProgramState> list) {
        programStateList = list;
    }

    @Override
    public List<ProgramState> getProgramStateList() {
        return programStateList;
    }

}
