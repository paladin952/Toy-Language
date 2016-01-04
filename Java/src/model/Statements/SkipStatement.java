package model.Statements;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.*;
import model.ProgramState;

/**
 * Created by Lucian on 11/10/2015.
 */
public class SkipStatement implements IStatement{

    @Override
    public String toString() {
        return "Skip";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws DivideByZeroException, ValueNotFoundException {
        //do nothing
        return null;
    }
}
