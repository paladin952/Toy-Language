package model.Statements;

import interfaces.IStatement;
import interfaces.Expression;

/**
 * Created by Lucian on 10/11/2015.
 */
public class AssignStatement implements IStatement {

    /**
     * The name of the variable
     */
    private String variableName;

    /**
     * The expression to be assigned to variable
     */
    private Expression expression;

    /**
     * The constructor
     * @param variableName The name of variable
     * @param expression The expression
     */
    public AssignStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    /**
     * Getters and setters
     */
    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * String representation
     * @return
     */
    @Override
    public String toString() {
        return variableName + "=" + expression.toString();
    }
}
