package interfaces;

/**
 * Created by Lucian on 10/12/2015.
 */
public interface IList<T> {

    void add(T element);

    T get(int position);

    void remove(int position);

    boolean isEmpty();

    int size();
}
