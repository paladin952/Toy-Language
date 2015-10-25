package interfaces;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lucian on 10/11/2015.
 */
public interface IDictionary<K, V> {

    V lookUp(K id);

    boolean isEmpty();

    int size();

    boolean containsKey(K key);

    void put(K key, V value);

    void remove(K key);

    String toString();

}
