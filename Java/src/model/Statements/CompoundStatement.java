package model.Statements;

import interfaces.IStatement;

/**
 * Created by Lucian on 10/11/2015.
 */
public class CompoundStatement implements IStatement {

    /**
     * First statement
     */
    private IStatement firstStatement;

    /**
     * Second statement
     */
    private IStatement secondStatement;

    /**
     * The constructor
     * @param firstStatement The first statement
     * @param secondStatement The second statement
     */
    public CompoundStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    /**
     * Getters and setters
     */
    public IStatement getFirstStatement() {
        return firstStatement;
    }

    public void setFirstStatement(IStatement firstStatement) {
        this.firstStatement = firstStatement;
    }

    public IStatement getSecondStatement() {
        return secondStatement;
    }

    public void setSecondStatement(IStatement secondStatement) {
        this.secondStatement = secondStatement;
    }

    /**
     * String representation
     */
    @Override
    public String toString() {
        return firstStatement.toString() + ";" + secondStatement.toString();
    }
}
