package interfaces;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;

import java.io.Serializable;

/**
 * Created by Lucian on 10/11/2015.
 */
public interface IStatement extends Serializable{

    /**
     * String representation
     *
     * @return String
     */
    String toString();

    /**
     * run one step for the current statement
     * @param myStack The stack
     * @param heap The heap
     * @param myDictionary The dictionary
     * @param output The output list
     * @throws DivideByZeroException in case of division with 0
     * @throws ValueNotFoundException If no value is found in the table
     */
    void oneStep(IStack<IStatement> myStack, IHeap<Integer, Integer>heap, IDictionary<String, Integer> myDictionary,
                 IList<String> output) throws DivideByZeroException, ValueNotFoundException;
}
