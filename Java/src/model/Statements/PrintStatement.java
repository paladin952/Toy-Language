package model.Statements;

import interfaces.IStatement;
import interfaces.Expression;

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
}
