package interfaces;

import java.util.Queue;

/**
 * Created by Lucian on 10/12/2015.
 */
public interface IStack<T> {

    T pop();

    void push(T exec);

    T top();

    boolean isEmpty();

    int size();

}
