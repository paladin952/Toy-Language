package model.Expresions;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.Expression;
import interfaces.IDictionary;
import interfaces.IHeap;

/**
 * Created by Lucian on 11/23/2015.
 */
public class ReadHeap implements Expression {

    /**
     * Variable name
     */
    private String variableName;

    /**
     * The constructor
     * @param variableName The variable name
     */
    public ReadHeap(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public int eval(IDictionary<String, Integer> table, IHeap<Integer, Integer> heap) throws ValueNotFoundException, DivideByZeroException {
        return heap.lookUp(table.lookUp(variableName));
    }

    @Override
    public String toString() {
        return "ReadHeap( "+ variableName + " )";
    }
}
