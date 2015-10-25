package model.Statements;

import interfaces.IStatement;
import model.Expresions.Expression;

/**
 * Created by Lucian on 10/11/2015.
 */
public class IfStatement implements IStatement {

    private Expression expression;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return "IF(" + expression.toString() +")THEN(" + thenStatement.toString() + ")ELSE("
                + elseStatement.toString() +")";
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public IStatement getThenStatement() {
        return thenStatement;
    }

    public void setThenStatement(IStatement thenStatement) {
        this.thenStatement = thenStatement;
    }

    public IStatement getElseStatement() {
        return elseStatement;
    }

    public void setElseStatement(IStatement elseStatement) {
        this.elseStatement = elseStatement;
    }
}
