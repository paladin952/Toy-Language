package model.Collections;

import interfaces.IStack;

import java.util.Stack;

/**
 * Created by Lucian on 11/2/2015.
 */
public class WrapperStack<T> implements IStack<T> {

    /**
     * Storage stack
     */
    private Stack<T> stack;

    /**
     * The constructor
     */
    public WrapperStack(){
        stack = new Stack<>();
    }

    /**
     * Get last element from stack
     * Delete it from stack
     *
     * @return T element
     */
    @Override
    public T pop() {
        return stack.pop();
    }

    /**
     * Push an element in top of the stack
     *
     * @param element The element
     */
    @Override
    public void push(T element) {
        stack.push(element);
    }

    /**
     * Get last element from stack
     *
     * @return T element
     */
    @Override
    public T top() {
        return stack.peek();
    }

    /**
     * Check if the stack is empty
     *
     * @return True if yes, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Get the size of the stack
     *
     * @return int size
     */
    @Override
    public int size() {
        return stack.size();
    }

    /** String representation
    ** @return String
    */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(T element : stack){
            result.append(element.toString());
            result.append("\n");
        }
        return result.toString();
    }
}
