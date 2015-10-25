package model.Expresions;

import interfaces.IDictionary;
import model.OperatorsEnum;

/**
 * Created by Lucian on 10/11/2015.
 */
public class ArithmeticExpression extends Expression {
    private Expression mFirstExpression;
    private Expression mSecondExpression;
    private OperatorsEnum mOperatorType;
    private String mOperator;

    public ArithmeticExpression(String operatorType, Expression firstExpression, Expression secondExpression) {
        this.mOperatorType = getOperatorType(operatorType);
        this.mFirstExpression = firstExpression;
        this.mSecondExpression = secondExpression;

    }

    private OperatorsEnum getOperatorType(String operator) {
        mOperator = operator;
        switch (operator) {
            case "+":
                return OperatorsEnum.ADD;
            case "-":
                return OperatorsEnum.SUBSTRACT;
            case "*":
                return OperatorsEnum.MULTIPLY;
            default:
                return OperatorsEnum.DIVIDE;
        }
    }

    @Override
    public int eval(IDictionary<String, Integer> table) {
        switch (mOperatorType) {
            case ADD:
                return (int)(mFirstExpression.eval(table) + mSecondExpression.eval(table));
            case SUBSTRACT:
                return (int)(mFirstExpression.eval(table) - mSecondExpression.eval(table));
            case MULTIPLY:
                return (int)(mFirstExpression.eval(table) * mSecondExpression.eval(table));
            case DIVIDE:
                return (int)(mFirstExpression.eval(table) + mSecondExpression.eval(table));
            default:
                return Integer.MIN_VALUE;
        }
    }

    @Override
    public String toString() {
        return mFirstExpression.toString() + mOperator + mSecondExpression.toString();
    }
}
