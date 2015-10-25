package model.Statements;

import interfaces.IStatement;
import model.Expresions.Expression;

/**
 * Created by Lucian on 10/11/2015.
 */
public class IfStatement implements IStatement {

    private Expression mExpression;
    private IStatement mThenStatement;
    private IStatement mElseStatement;

    public IfStatement(Expression mExpression, IStatement mThenStatement, IStatement mElseStatement) {
        this.mExpression = mExpression;
        this.mThenStatement = mThenStatement;
        this.mElseStatement = mElseStatement;
    }

    @Override
    public String toString() {
        return "IF(" + mExpression.toString() +")THEN(" + mThenStatement.toString() + ")ELSE("
                + mElseStatement.toString() +")";
    }

    public Expression getmExpression() {
        return mExpression;
    }

    public void setmExpression(Expression mExpression) {
        this.mExpression = mExpression;
    }

    public IStatement getmThenStatement() {
        return mThenStatement;
    }

    public void setmThenStatement(IStatement mThenStatement) {
        this.mThenStatement = mThenStatement;
    }

    public IStatement getmElseStatement() {
        return mElseStatement;
    }

    public void setmElseStatement(IStatement mElseStatement) {
        this.mElseStatement = mElseStatement;
    }
}
