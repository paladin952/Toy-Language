package interfaces;

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
    int eval(IDictionary<String, Integer> table);


    /**
     * The string representation
     * @return String
     */
    String toString();
}
