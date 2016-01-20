package model.Statements;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.IDictionary;
import interfaces.IStack;
import interfaces.IStatement;
import model.Collections.WrapperDictionary;
import model.Collections.WrapperStack;
import model.ProgramState;

import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by Clapa Lucian on 1/4/2016.
 */
public class ForkStatement implements IStatement {

    /**
     * The statement to be forked
     */
    private IStatement statement;

    /**
     * The constructor
     * @param statement The statement
     */
    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws DivideByZeroException, ValueNotFoundException {
        IStack<IStatement>stack = new WrapperStack<>();
        stack.push(statement);

//        IDictionary<String, Integer>copyDictionary = new WrapperDictionary<>();
//        copyDictionary.addAll(programState.getMyDictionary().getMap());

        Object copy = programState.getStackDictionary().clone();
        Stack<IDictionary<String, Integer>> copyStack = (Stack<IDictionary<String, Integer>>)stack;

        return new ProgramState(stack, copyStack, programState.getOutput(), programState.getHeap(), statement);
    }

    /**
     * String representation
     * @return String
     */
    @Override
    public String toString() {
        return "Fork(" + statement.toString() + ")";

    }
}
