package model.Collections;

import interfaces.IList;

import java.lang.reflect.Array;

/**
 * Created by Lucian on 10/12/2015.
 */
public class MyList<T> implements IList<T> {

    private T[] array;
    private int size;

    public MyList(Class<T> cls, int size) {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(cls, size);
        this.array = a;
        this.size = 0;
    }

    @Override
    public void add(T element) {
        array[size] = element;
        size++;
    }

    @Override
    public T get(int position) {
        return array[position];
    }

    @Override
    public void remove(int position) {
        for(int i = position; i < size-1; i++){
            array[i] = array[i+1];
        }
        size --;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < size; i++){
            result.append(array[i].toString() + "\n");
        }
        return result.toString();
    }
}
