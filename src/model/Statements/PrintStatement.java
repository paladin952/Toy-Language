package model.Statements;

import interfaces.IStatement;
import model.Expresions.Expression;

/**
 * Created by Lucian on 10/11/2015.
 */
public class PrintStatement implements IStatement {
    private Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression mExpression) {
        this.expression = mExpression;
    }

    @Override
    public String toString() {
        return "Print(" + expression.toString() + ")";

    }
}
