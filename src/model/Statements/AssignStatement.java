package model.Statements;

import interfaces.IStatement;
import model.Expresions.Expression;

/**
 * Created by Lucian on 10/11/2015.
 */
public class AssignStatement implements IStatement {

    private String mId;
    private Expression mExpression;

    public AssignStatement(String mId, Expression mExpression) {
        this.mId = mId;
        this.mExpression = mExpression;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public Expression getmExpression() {
        return mExpression;
    }

    public void setmExpression(Expression mExpression) {
        this.mExpression = mExpression;
    }

    @Override
    public String toString() {
        return mId + "=" + mExpression.toString();
    }
}
