package model.Expresions;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.Expression;
import interfaces.IDictionary;
import model.OperatorsEnum;

/**
 * Created by Lucian on 11/3/2015.
 */
public class LogicExpression implements Expression {

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

    public LogicExpression(String operator, Expression expression1, Expression expression2) {
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
            case "&&":
                return OperatorsEnum.AND;
            case "||":
                return OperatorsEnum.OR;
            default:
                return OperatorsEnum.NOT;
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
    public int eval(IDictionary<String, Integer> table) throws ValueNotFoundException, DivideByZeroException {

        boolean firstBoolean;
        boolean secondBoolean;

        if(firstExpression.eval(table) == 0){
            firstBoolean = false;
        }else{
            firstBoolean = true;
        }

        if(firstExpression.eval(table) == 0){
            secondBoolean = false;
        }else{
            secondBoolean = true;
        }

        switch (operatorType) {
            case AND:
                if(firstBoolean && secondBoolean){
                    return 1;
                }
                return 0;
            case OR:
                if(firstBoolean || secondBoolean){
                    return 1;
                }
                return 0;
            case NOT:
                if (!firstBoolean) {
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
