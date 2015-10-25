package model.Expresions;

import interfaces.Expression;
import interfaces.IDictionary;
import model.OperatorsEnum;

/**
 * Created by Lucian on 10/11/2015.
 */
public class ArithmeticExpression implements Expression {

    /**
     * First expression
     */
    private Expression firstExpression;

    /**
     * Second expression
     */
    private Expression secondExpression;

    /**
     * The type of operator
     */
    private OperatorsEnum operatorType;

    /**
     * The operator as string
     */
    private String operator;

    /**
     * The constructor
     * @param operatorType The type of the operator for current arithmetic expression
     * @param firstExpression The first expression
     * @param secondExpression The second expression
     */
    public ArithmeticExpression(String operatorType, Expression firstExpression, Expression secondExpression) {
        this.operatorType = getOperatorType(operatorType);
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    /**
     * Getting the operator type from string
     * @param operator The String operator
     * @return OperatorsEnum
     */
    private OperatorsEnum getOperatorType(String operator) {
        this.operator = operator;
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

    /**
     * Evaluates the expression
     * For simplicity the result will be converted to int
     * @param table The table of values in case of a variable
     * @return int result
     */
    @Override
    public int eval(IDictionary<String, Integer> table) {
        switch (operatorType) {
            case ADD:
                return (int)(firstExpression.eval(table) + secondExpression.eval(table));
            case SUBSTRACT:
                return (int)(firstExpression.eval(table) - secondExpression.eval(table));
            case MULTIPLY:
                return (int)(firstExpression.eval(table) * secondExpression.eval(table));
            case DIVIDE:
                return (int)(firstExpression.eval(table) + secondExpression.eval(table));
            default:
                return Integer.MIN_VALUE;
        }
    }

    /**
     * String representation
     * @return String
     */
    @Override
    public String toString() {
        return firstExpression.toString() + operator + secondExpression.toString();
    }
}
