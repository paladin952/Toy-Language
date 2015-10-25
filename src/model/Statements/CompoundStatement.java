package model.Statements;

import interfaces.IStatement;

/**
 * Created by Lucian on 10/11/2015.
 */
public class CompoundStatement implements IStatement {

    private IStatement mFirstStatement;
    private IStatement mSecondStatement;

    public CompoundStatement(IStatement mFirstStatement, IStatement mSecondStatement) {
        this.mFirstStatement = mFirstStatement;
        this.mSecondStatement = mSecondStatement;
    }

    public IStatement getmFirstStatement() {
        return mFirstStatement;
    }

    public void setmFirstStatement(IStatement mFirstStatement) {
        this.mFirstStatement = mFirstStatement;
    }

    public IStatement getmSecondStatement() {
        return mSecondStatement;
    }

    public void setmSecondStatement(IStatement mSecondStatement) {
        this.mSecondStatement = mSecondStatement;
    }

    @Override
    public String toString() {
        return mFirstStatement.toString() + ";" + mSecondStatement.toString();
    }
}
