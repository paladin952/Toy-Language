package model.Collections;

import interfaces.IDictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lucian on 11/2/2015.
 */
public class WrapperDictionary<K, V> implements IDictionary<K, V> {

    /**
     * The storage map
     */
    protected Map<K, V> map;

    /**
     * The constructor
     */
    public WrapperDictionary() {
        this.map = new HashMap<>();
    }

    /**
     * Get the value of the id
     *
     * @param id The id
     * @return Value
     */
    @Override
    public V lookUp(K id) {
        return map.get(id);
    }

    /**
     * Check if dictionary is empty
     *
     * @return True if yes, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Get the size of the dictionary
     *
     * @return int size
     */
    @Override
    public int size() {
        return map.size();
    }

    /**
     * Check if K key is in dictionary
     *
     * @param key The key
     * @return True if yes, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    /**
     * Put the V value in K key
     *
     * @param key   The key
     * @param value The value
     */
    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    /**
     * Remove the key from dictionary
     *
     * @param key The key
     */
    @Override
    public void remove(K key) {
        map.remove(key);
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
        for (Map.Entry<K, V> entry : map.entrySet())
        {
            result.append(entry.getKey() + "->" + entry.getValue() + "\n");
        }

        return result.toString();
    }

    @Override
    public void addAll(Map<K, V> all) {
        map.clear();
        map.putAll(all);
    }

    @Override
    public Map<K, V> getMap() {
        return map;
    }
}
