package model.Statements;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.*;
import model.ProgramState;

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

    @Override
    public ProgramState execute(ProgramState programState) throws DivideByZeroException, ValueNotFoundException {
        Expression expression = getExpression();
        String id = getVariableName();
        IDictionary<String, Integer> myDictionary = programState.getMyDictionary();
        IHeap<Integer, Integer> heap = programState.getHeap();
        int val = expression.eval(myDictionary, heap);
        //insert or update
        myDictionary.put(id, val);
        return null;
    }
}
