package model.Collections;

import interfaces.IHeap;

import java.util.Map;

/**
 * Created by Lucian on 11/21/2015.
 */
public class MyHeap extends WrapperDictionary<Integer, Integer> implements IHeap<Integer, Integer> {
    //The heap hass same implementation as the dictionary because it is
    //using the key->value structure just with different data types

}
