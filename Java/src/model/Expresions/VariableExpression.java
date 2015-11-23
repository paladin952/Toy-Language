package model.Expresions;

import Exceptions.ValueNotFoundException;
import interfaces.Expression;
import interfaces.IDictionary;
import interfaces.IHeap;

/**
 * Created by Lucian on 10/11/2015.
 */
public class VariableExpression implements Expression {

    /**
     * The name of the variable
     */
    private String name;

    /**
     * Constructor
     * @param name The id of variable
     */
    public VariableExpression(String name) {
        this.name = name;
    }

    /**
     * Evaluate the variable by getting its value from the Table
     * @param table Table of values
     * @return int value
     */
    @Override
    public int eval(IDictionary<String, Integer> table, IHeap<Integer, Integer> heap) throws ValueNotFoundException {
        return table.lookUp(name);
    }

    /**
     * String representation
     * @return String
     */
    @Override
    public String toString() {
        return name;
    }
}
