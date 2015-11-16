package interfaces;

import Exceptions.EmptyStackException;

/**
 * Created by Lucian on 10/12/2015.
 */
public interface IStack<T> extends java.io.Serializable{

    /**
     * Get the top element and decrement the stack
     *
     * @return T element
     */
    T pop() throws EmptyStackException;

    /**
     * Push element T at the top of the stack
     *
     * @param element The element
     */
    void push(T element);

    /**
     * Get the top element from the stack
     *
     * @return T element
     */
    T top() throws EmptyStackException;

    /**
     * Check if the stack is empty
     *
     * @return True if yes, false otherwise
     */
    boolean isEmpty();

    /**
     * Get the size of the stack
     *
     * @return Int size
     */
    int size();

}
