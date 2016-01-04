package threads;

import Exceptions.*;
import controller.Controller;
import model.ProgramState;
import utils.Constants;

/**
 * Created by Clapa Lucian on 1/4/2016.
 */
public class ForkThread implements Runnable {

    /**
     * The program state
     */
    private ProgramState programState;

    /**
     * The print listener
     */
    Controller.PrintState listener;

    /**
     * The constructor
     * @param programState The new program state
     */
    public ForkThread(ProgramState programState, Controller.PrintState listener) {
        this.programState = programState;
        this.listener = listener;
    }


    @Override
    public void run() {
        try {
            programState.oneStep(programState);
            if (Controller.PRINT_FLAG == Constants.PRINT_CONSOLE) {
                System.out.println("Id=2");
                listener.print(programState.toString());
            }
        } catch (StatementExecutionException e) {
            e.printStackTrace();
        } catch (EmptyStackException e) {
            e.printStackTrace();
        } catch (ValueNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        } catch (DivideByZeroException e) {
            e.printStackTrace();
        }
    }
}
