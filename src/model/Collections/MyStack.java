package model.Collections;

import interfaces.IStack;

import java.lang.reflect.Array;

/**
 * Created by Lucian on 10/12/2015.
 */
public class MyStack<T> implements IStack<T> {

    private T[] array;
    private int size;

    public MyStack(Class<T> cls, int size) {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(cls, size);
        this.array = a;
        this.size = 0;
    }

    @Override
    public T pop() {
        if(size()>0){
            T element = array[size -1];
            size--;
            return element;
        }
        return null;
    }

    @Override
    public void push(T exec) {
        array[size] = exec;
        size++;
    }

    @Override
    public T top() {
        return array[size-1];
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
        for(int i =size -1; i>=0; i--){
            result.append(array[i].toString());
            result.append("\n");
        }
        return result.toString();
    }
}
