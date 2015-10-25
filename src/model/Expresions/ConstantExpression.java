package model.Expresions;

import interfaces.IDictionary;

/**
 * Created by Lucian on 10/11/2015.
 */
public class ConstantExpression extends Expression {
    private int number;

    public ConstantExpression(int number) {
        this.number = number;
    }

    @Override
    public int eval(IDictionary<String, Integer> table){
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
