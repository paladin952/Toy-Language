package model.Expresions;

import interfaces.Expression;
import interfaces.IDictionary;

/**
 * Created by Lucian on 10/11/2015.
 */
public class ConstantExpression implements Expression {

    /**
     * The number
     */
    private int number;

    /**
     * The constructor
     * @param number int number
     */
    public ConstantExpression(int number) {
        this.number = number;
    }

    /**
     * Evaluate the expression by return the number
     * @param table Table of values
     * @return int number
     */
    @Override
    public int eval(IDictionary<String, Integer> table){
        return number;
    }

    /**
     * String representation
     * @return String
     */
    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
