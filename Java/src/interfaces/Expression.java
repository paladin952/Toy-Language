package interfaces;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.IDictionary;

import java.io.Serializable;

/**
 * Created by Lucian on 10/11/2015.
 */
public interface Expression extends Serializable {

    /**
     *Evaluating the expression
     * @param table Table of values
     * @return int result (for simplicity)
     */
    int eval(IDictionary<String, Integer> table, IHeap<Integer, Integer>heap) throws ValueNotFoundException, DivideByZeroException;


    /**
     * The string representation
     * @return String
     */
    String toString();
}
