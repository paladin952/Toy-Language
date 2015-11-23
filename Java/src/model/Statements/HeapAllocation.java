package model.Statements;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.Expression;
import interfaces.IDictionary;
import interfaces.IHeap;
import interfaces.IStatement;
import model.Collections.MyHeap;

/**
 * Created by Lucian on 11/21/2015.
 */
public class HeapAllocation implements IStatement {

    /**
     * The varoiable name
     */
    private String variableName;

    private Expression expression;

    /**
     * Private contructor, use builder
     */
    public HeapAllocation(String variableName, Expression expression){
        this.variableName = variableName;
//        this.expressionResult = expression.eval(symbolicTable);
        this.expression = expression;
//        pointer = heap.size();
//        heap.put(heap.size() ,expressionResult);
//        symbolicTable.put(variableName, pointer);
    }


    public String getVariableName() {
        return variableName;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "HeapAllocation{ " + variableName + " -> " + expression.toString() + " }";
    }
}
