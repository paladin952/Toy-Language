package model.Statements;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.*;

/**
 * Created by Lucian on 11/10/2015.
 */
public class SwitchStatement implements IStatement {

    private Expression expression;
    private Expression case1;
    private Expression case2;

    private IStatement statementDefault;
    private IStatement statementCase1;
    private IStatement statementCase2;

    public SwitchStatement(Expression expression, Expression case1, Expression case2,
                           IStatement statementDefault, IStatement statementCase1, IStatement statementCase2) {

        this.expression = expression;
        this.case1 = case1;
        this.case2 = case2;
        this.statementDefault = statementDefault;
        this.statementCase1 = statementCase1;
        this.statementCase2 = statementCase2;
    }

    @Override
    public String toString() {
        return "switch(" + expression.toString() + ")\n{"
                + "case " + case1.toString() + "\n" +  statementCase1.toString()
                + "\ncase " + case2.toString() + "\n" + statementCase2.toString()
                + "\ndefault " + statementDefault.toString();
    }

    @Override
    public void oneStep(IStack<IStatement> myStack, IHeap<Integer, Integer> heap, IDictionary<String, Integer> myDictionary, IList<String> output) throws DivideByZeroException, ValueNotFoundException {
        Expression expression = getExpression();
        if (getCase1().eval(myDictionary, heap) == expression.eval(myDictionary, heap)) {
            myStack.push(getStatementCase1());
        }
        if (getCase2().eval(myDictionary, heap) == expression.eval(myDictionary, heap)) {
            myStack.push(getStatementCase2());
        }
        myStack.push(getStatementDefault());
    }

    public Expression getExpression() {
        return expression;
    }

    public Expression getCase1() {
        return case1;
    }

    public Expression getCase2() {
        return case2;
    }

    public IStatement getStatementDefault() {
        return statementDefault;
    }

    public IStatement getStatementCase1() {
        return statementCase1;
    }

    public IStatement getStatementCase2() {
        return statementCase2;
    }
}
