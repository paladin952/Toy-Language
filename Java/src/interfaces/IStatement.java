package interfaces;

import Exceptions.*;
import model.ProgramState;

import java.io.Serializable;

/**
 * Created by Lucian on 10/11/2015.
 */
public interface IStatement extends Serializable{

    /**
     * String representation
     *
     * @return String
     */
    String toString();

    /**
     * run one step for the current statement
     */
    ProgramState execute(ProgramState programState) throws DivideByZeroException, ValueNotFoundException, StatementExecutionException, InvalidPositionException, EmptyStackException;
}
