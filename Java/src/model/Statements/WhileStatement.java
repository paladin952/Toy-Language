package model.Statements;

import Exceptions.*;
import interfaces.*;
import model.Collections.WrapperStack;
import model.ProgramState;

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

    @Override
    public void oneStep(IStack<IStatement> myStack, IHeap<Integer, Integer> heap, IDictionary<String, Integer> myDictionary, IList<String> output) throws DivideByZeroException, ValueNotFoundException {
        //TODO think how to solve the coupling with the controller
    }
}
