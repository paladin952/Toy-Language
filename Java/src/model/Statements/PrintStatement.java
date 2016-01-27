package model.Statements;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.*;
import model.ProgramState;

/**
 * Created by Lucian on 10/11/2015.
 */
public class PrintStatement implements IStatement {

    /**
     * The expression to be printed
     */
    private Expression expression;

    /**
     * The constructor
     * @param expression Expression
     */
    public PrintStatement(Expression expression) {
        this.expression = expression;
    }


    /**
     * Getters and setters
     */

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression mExpression) {
        this.expression = mExpression;
    }


    /**
     * String representation
     * @return String
     */
    @Override
    public String toString() {
        return "Print(" + expression.toString() + ")";

    }

    @Override
    public ProgramState execute(ProgramState programState) throws DivideByZeroException, ValueNotFoundException {
        IHeap<Integer, Integer> heap = programState.getHeap();
        IDictionary<String, Integer> myDictionary = programState.getMyDictionary();
        IList<String> output = programState.getOutput();
        Expression expr = getExpression();
        output.add(String.valueOf(expr.eval(myDictionary, heap)));
        return null;
    }
}
