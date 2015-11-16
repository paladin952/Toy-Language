package model.Collections;

import interfaces.IList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucian on 11/2/2015.
 */
public class WrapperList<T> implements IList<T>{

    /**
     * The storage list
     */
    private List<T> data;

    /**
     * The constructor
     */
    public WrapperList(){
        data = new ArrayList<>();
    }

    /**
     * Add an element in list
     *
     * @param element T element
     */
    @Override
    public void add(T element) {
        data.add(element);
    }

    /**
     * Get element from list by position
     *
     * @param position Int position
     * @return The element
     */
    @Override
    public T get(int position) {
        return data.get(position);
    }

    /**
     * Remove element from list by position
     *
     * @param position Int position
     */
    @Override
    public void remove(int position) {
        data.remove(position);
    }

    /**
     * Check if list is empty
     *
     * @return True if yes, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Get the size of the list
     *
     * @return int size
     */
    @Override
    public int size() {
        return data.size();
    }

    /**
     * String representation
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(T element : data){
            result.append(element.toString() + "\n");
        }
        return result.toString();
    }
}
