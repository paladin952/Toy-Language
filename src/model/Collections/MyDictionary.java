package model.Collections;

import interfaces.IDictionary;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Lucian on 10/12/2015.
 */
public class MyDictionary<K, V> implements IDictionary<K, V> {

    private Tuple[] array;
    private int size;

    private class Tuple{

        private K key;
        private V value;

        public Tuple(K key, V value) {
            this.key = key;
            this.value = value;
        }


        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public MyDictionary(int size) {
        this.size = 0;
        Object[] tmp = new Object[size];
        array = Arrays.copyOf(tmp, tmp.length, Tuple[].class);
    }

    @Override
    public V lookUp(K id) {
        for(int i =0; i < size; i++){
            if (array[i].getKey().equals(id)){
                return array[i].getValue();
            }
        }
        return null;
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
    public boolean containsKey(K key) {
        for(int i =0; i < size; i++){
            if (array[i].getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void put(K key, V value) {
        boolean found = false;
        for(int i =0; i < size; i++){
            if (array[i].getKey().equals(key)){
                array[i].setValue(value);
                found = true;
            }
        }

        if(!found){
            array[size] = new Tuple(key, value);
            size++;
        }
    }

    @Override
    public void remove(K key) {
        for(int i =0; i< size; i++){
            if(array[i].getKey().equals(key)){
                for(int j = i; j < size -1; j++){
                    array[j] = array[j+1];
                }
                size --;
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i =0; i< size; i++){
            result.append(array[i].key + "->" + array[i].value+"\n");
        }
        return result.toString();
    }
}
