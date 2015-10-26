package interfaces;

/**
 * Created by Lucian on 10/11/2015.
 */
public interface IDictionary<K, V> {

    /**
     * Get the value at id K
     *
     * @param id The id
     * @return The value V of id K
     */
    V lookUp(K id);

    /**
     * Check if dictionary is empty
     *
     * @return True if yes, false otherwise
     */
    boolean isEmpty();

    /**
     * Get the size of the dictionary
     *
     * @return Int size
     */
    int size();

    /**
     * Check if key K is in dictioinary
     *
     * @param key The key
     * @return true if yes, false otherwise
     */
    boolean containsKey(K key);

    /**
     * Put value V in key K if exists key
     * Create a new key and  a new value if key doesn't exists
     *
     * @param key   The key
     * @param value The value
     */
    void put(K key, V value);

    /**
     * Remove from dictionary the structure at key
     *
     * @param key The key
     */
    void remove(K key);

    /**
     * String representation
     *
     * @return string
     */
    String toString();

}
