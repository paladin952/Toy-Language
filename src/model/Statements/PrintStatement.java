package model.Statements;

import interfaces.IStatement;
import model.Expresions.Expression;

/**
 * Created by Lucian on 10/11/2015.
 */
public class PrintStatement implements IStatement {
    private Expression mExpression;

    public PrintStatement(Expression mExpression) {
        this.mExpression = mExpression;
    }

    public Expression getmExpression() {
        return mExpression;
    }

    public void setmExpression(Expression mExpression) {
        this.mExpression = mExpression;
    }

    @Override
    public String toString() {
        return "Print(" + mExpression.toString() + ")";

    }
}
