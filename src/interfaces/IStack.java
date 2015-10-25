package interfaces;

/**
 * Created by Lucian on 10/12/2015.
 */
public interface IStack<T> {

    /**
     * Get the top element and decrement the stack
     *
     * @return T element
     */
    T pop();

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
    T top();

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
