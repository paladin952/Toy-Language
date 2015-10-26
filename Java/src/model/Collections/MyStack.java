package model.Collections;

import interfaces.IStack;

import java.lang.reflect.Array;

public class MyStack<T> implements IStack<T> {

    /**
     * The array
     */
    private T[] array;

    /**
     * The size of the stack
     */
    private int size;

    /**
     * The constructor
     *
     * @param cls      The class of T
     * @param capacity The max capacity
     */
    public MyStack(Class<T> cls, int capacity) {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(cls, capacity);
        this.array = a;
        this.size = 0;
    }

    /**
     * Get last element from stack
     * Delete it from stack
     *
     * @return T element
     */
    @Override
    public T pop() {
        if (size() > 0) {
            T element = array[size - 1];
            size--;
            return element;
        }
        return null;
    }

    /**
     * Push an element in top of the stack
     *
     * @param element The element
     */
    @Override
    public void push(T element) {
        array[size] = element;
        size++;
    }

    /**
     * Get last element from stack
     *
     * @return T element
     */
    @Override
    public T top() {
        return array[size - 1];
    }

    /**
     * Check if the stack is empty
     *
     * @return True if yes, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Get the size of the stack
     *
     * @return int size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * String representation
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            result.append(array[i].toString());
            result.append("\n");
        }
        return result.toString();
    }
}
