package model.Statements;

import interfaces.Expression;
import interfaces.IStatement;

/**
 * Created by Lucian on 11/23/2015.
 */
public class WriteHeapStatement implements IStatement {

    /**
     * The variable name
     */
    private String variableName;

    /**
     * The expression
     */
    private Expression expression;

    /**
     * The constructor
     *
     * @param variableName The var name
     * @param expression   the expression
     */
    public WriteHeapStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "WriteHeap( " + variableName + "=" + expression.toString() + " )";
    }
}
