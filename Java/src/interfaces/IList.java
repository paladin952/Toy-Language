package interfaces;

/**
 * Created by Lucian on 10/12/2015.
 */
public interface IList<T> {

    /**
     * Add a new element in list
     *
     * @param element T element
     */
    void add(T element);

    /**
     * Get the element from list by position
     *
     * @param position Int position
     * @return T element
     */
    T get(int position);

    /**
     * Remove element T at position
     *
     * @param position Int position
     */
    void remove(int position);

    /**
     * Check if list is empty
     *
     * @return True if empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Get the size of the list
     *
     * @return Int size
     */
    int size();
}
