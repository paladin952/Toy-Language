package model.Collections;

import interfaces.IDictionary;

import java.util.Arrays;

/**
 * Created by Lucian on 10/12/2015.
 */
public class MyDictionary<K, V> implements IDictionary<K, V> {

    /**
     * An array of tuple (key, value)
     */
    private Tuple[] array;

    /**
     * The size of the dictionary
     */
    private int size;

    /**
     * Inner class Tuple
     */
    private class Tuple {

        /**
         * The key
         */
        private K key;

        /**
         * The value
         */
        private V value;

        /**
         * The constructor
         *
         * @param key   The key
         * @param value The value
         */
        public Tuple(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Get the key
         *
         * @return Key
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key
         *
         * @param key Key
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Get the value of current key
         *
         * @return Value
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the value of the current key
         *
         * @param value Value
         */
        public void setValue(V value) {
            this.value = value;
        }
    }

    /**
     * Constructor
     *
     * @param size int max size of dictionary
     */
    public MyDictionary(int size) {
        this.size = 0;
        Object[] tmp = new Object[size];
        array = Arrays.copyOf(tmp, tmp.length, Tuple[].class);
    }

    /**
     * Get the value of the id
     *
     * @param id The id
     * @return Value
     */
    @Override
    public V lookUp(K id) {
        for (int i = 0; i < size; i++) {
            if (array[i].getKey().equals(id)) {
                return array[i].getValue();
            }
        }

        //if the key is not here return null
        return null;
    }

    /**
     * Check if dictionary is empty
     *
     * @return True if yes, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Get the size of the dictionary
     *
     * @return int size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Check if K key is in dictionary
     *
     * @param key The key
     * @return True if yes, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (array[i].getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Put the V value in K key
     *
     * @param key   The key
     * @param value The value
     */
    @Override
    public void put(K key, V value) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (array[i].getKey().equals(key)) {
                array[i].setValue(value);
                found = true;
            }
        }

        if (!found) {
            array[size] = new Tuple(key, value);
            size++;
        }
    }

    /**
     * Remove the key from dictionary
     *
     * @param key The key
     */
    @Override
    public void remove(K key) {
        for (int i = 0; i < size; i++) {
            if (array[i].getKey().equals(key)) {
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                size--;
                break;
            }
        }
    }

    /**
     * String representation
     *
     * @return String
     *
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(array[i].key + "->" + array[i].value + "\n");
        }
        return result.toString();
    }
}
