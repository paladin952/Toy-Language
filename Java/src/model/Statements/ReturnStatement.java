package model.Statements;

import Exceptions.*;
import interfaces.IStatement;
import model.ProgramState;

/**
 * Created by Clapa Lucian on 1/20/2016.
 */
public class ReturnStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState programState) throws DivideByZeroException, ValueNotFoundException, StatementExecutionException, InvalidPositionException, EmptyStackException {

        programState.getExecutionStack().pop();
        return null;
    }
}
