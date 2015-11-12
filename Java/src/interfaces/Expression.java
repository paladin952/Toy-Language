package interfaces;

import Exceptions.DivideByZeroException;
import Exceptions.ValueNotFoundException;
import interfaces.IDictionary;

/**
 * Created by Lucian on 10/11/2015.
 */
public interface Expression {

    /**
     *Evaluating the expression
     * @param table Table of values
     * @return int result (for simplicity)
     */
    int eval(IDictionary<String, Integer> table) throws ValueNotFoundException, DivideByZeroException;


    /**
     * The string representation
     * @return String
     */
    String toString();
}
