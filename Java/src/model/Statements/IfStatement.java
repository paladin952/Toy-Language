package model.Statements;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.*;

/**
 * Created by Lucian on 10/11/2015.
 */
public class IfStatement implements IStatement {

    /**
     * The expression to be evaluated
     */
    private Expression expression;

    /**
     * The statement to be called if expression is true
     */
    private IStatement thenStatement;

    /**
     * The statement to be called if expression is false
     */
    private IStatement elseStatement;

    /**
     * The constructor
     * @param expression The expression
     * @param thenStatement The correct statement
     * @param elseStatement The false statement
     */
    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    /**
     * String representation
     * @return String
     */
    @Override
    public String toString() {
        if(elseStatement == null){
            return "IF(" + expression.toString() +")";
        }
        return "IF(" + expression.toString() +")THEN(" + thenStatement.toString() + ")ELSE("
                + elseStatement.toString() +")";
    }

    @Override
    public void oneStep(IStack<IStatement> myStack, IHeap<Integer, Integer> heap, IDictionary<String, Integer> myDictionary, IList<String> output) throws DivideByZeroException, ValueNotFoundException {
        if (getExpression().eval(myDictionary, heap) != 0) {
            myStack.push(getThenStatement());
        } else {
            if (getElseStatement() != null) {
                myStack.push(getElseStatement());
            }
        }
    }

    /**
     * Getters and setters
     */
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
