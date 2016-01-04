package model.Statements;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.*;
import model.ProgramState;

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
    public HeapAllocation(String variableName, Expression expression) {
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

    @Override
    public ProgramState execute(ProgramState programState) throws DivideByZeroException, ValueNotFoundException {
        IHeap<Integer, Integer> heap = programState.getHeap();
        IDictionary<String, Integer> myDictionary = programState.getMyDictionary();
        int pointer = heap.size();
        heap.put(pointer, getExpression().eval(myDictionary, heap));
        myDictionary.put(getVariableName(), pointer);
        return null;
    }
}
