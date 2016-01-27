package model.Expresions;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.Expression;
import interfaces.IDictionary;
import interfaces.IHeap;
import model.OperatorsEnum;

/**
 * Created by Lucian on 11/3/2015.
 */
public class BooleanExpression implements Expression {

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

    public BooleanExpression(String operator, Expression expression1, Expression expression2) {
        this.operatorType = getOperatorType(operator);
        this.firstExpression = expression1;
        this.secondExpression = expression2;
    }

    /**
     * Getting the operator type from string
     *
     * @param operator The String operator
     * @return OperatorsEnum
     */
    private OperatorsEnum getOperatorType(String operator) {
        this.operator = operator;
        switch (operator) {
            case "<":
                return OperatorsEnum.LESS;
            case "<=":
                return OperatorsEnum.LESS_EQUAL;
            case ">":
                return OperatorsEnum.GRATER;
            case ">=":
                return OperatorsEnum.GRATER_EQUAL;
            case "==":
                return OperatorsEnum.EQUAL;
            default:
                return OperatorsEnum.DIFFERENT;
        }
    }

    /**
     * Evaluates the expression
     * For simplicity the result will be converted to int
     *
     * @param table The table of values in case of a variable
     * @return int result
     */
    @Override
    public int eval(IDictionary<String, Integer> table, IHeap<Integer, Integer> heap) throws ValueNotFoundException, DivideByZeroException {
        switch (operatorType) {
            case LESS:
                if (firstExpression.eval(table, heap) < secondExpression.eval(table, heap)) {
                    return 1;
                }
                return 0;
            case LESS_EQUAL:
                if (firstExpression.eval(table, heap) <= secondExpression.eval(table, heap)) {
                    return 1;
                }
                return 0;
            case GRATER_EQUAL:
                if (firstExpression.eval(table, heap) >= secondExpression.eval(table, heap)) {
                    return 1;
                }
                return 0;
            case GRATER:
                if (firstExpression.eval(table, heap) > secondExpression.eval(table, heap)) {
                    return 1;
                }
                return 0;
            case EQUAL:
                if (firstExpression.eval(table, heap) == secondExpression.eval(table, heap)) {
                    return 1;
                }
                return 0;
            case DIFFERENT:
                if (firstExpression.eval(table, heap) != secondExpression.eval(table, heap)) {
                    return 1;
                }
                return 0;
            default:
                return Integer.MIN_VALUE;
        }
    }

    @Override
    public String toString() {
        return "(" + firstExpression.toString() + " " + operator + " " + secondExpression.toString() + ")";
    }
}
