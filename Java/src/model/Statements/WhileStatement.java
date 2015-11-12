package model.Statements;

import interfaces.Expression;
import interfaces.IStatement;

/**
 * Created by Lucian on 11/10/2015.
 */
public class WhileStatement implements IStatement {

    IStatement statement;

    Expression expression;

    public WhileStatement(IStatement statement, Expression expression) {
        this.statement = statement;
        this.expression = expression;
    }

    public IStatement getStatement() {
        return statement;
    }

    public void setStatement(IStatement statement) {
        this.statement = statement;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "While( " + expression.toString() + " )" + "{ " + statement.toString() + "\n}";
    }
}
