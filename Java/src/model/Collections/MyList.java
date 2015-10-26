package model.Collections;

import interfaces.IList;

import java.lang.reflect.Array;

/**
 * Created by Lucian on 10/12/2015.
 */
public class MyList<T> implements IList<T> {

    /**
     * The array
     */
    private T[] array;

    /**
     * The size of the list
     */
    private int size;

    /**
     * Constructor
     *
     * @param cls      the class of T
     * @param capacity The capacity
     */
    public MyList(Class<T> cls, int capacity) {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(cls, capacity);
        this.array = a;
        this.size = 0;
    }

    /**
     * Add an element in list
     *
     * @param element T element
     */
    @Override
    public void add(T element) {
        array[size] = element;
        size++;
    }

    /**
     * Get element from list by position
     *
     * @param position Int position
     * @return The element
     */
    @Override
    public T get(int position) {
        return array[position];
    }

    /**
     * Remove element from list by position
     *
     * @param position Int position
     */
    @Override
    public void remove(int position) {
        for (int i = position; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    /**
     * Check if list is empty
     *
     * @return True if yes, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Get the size of the list
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
        for (int i = 0; i < size; i++) {
            result.append(array[i].toString() + "\n");
        }
        return result.toString();
    }
}
