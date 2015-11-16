package tests;

import Exceptions.EmptyStackException;
import Exceptions.InvalidPositionException;
import Exceptions.ValueNotFoundException;
import interfaces.IDictionary;
import interfaces.IList;
import interfaces.IStack;
import model.Collections.MyDictionary;
import model.Collections.MyList;
import model.Collections.MyStack;
/**
 * Created by Lucian on 10/12/2015.
 */
public class CollectionTests {

    public CollectionTests() {
        testList();
        testStack();
        testDictionary();
    }

    /**
     * Test IDictionary
     */
    private void testDictionary() {
        IDictionary<String, Double> myDictionary = new MyDictionary<>(20);

        //test isEmpty and size
        assert myDictionary.isEmpty();
        assert myDictionary.size() == 0;

        myDictionary.put("key1", 1.0);
        assert myDictionary.size() == 1;
        assert !myDictionary.isEmpty();
        try {
            assert myDictionary.lookUp("key1").equals(1.0);
        } catch (ValueNotFoundException e) {
            e.printStackTrace();
        }

        myDictionary.remove("key1");
        assert myDictionary.isEmpty();
        assert myDictionary.size() == 0;


    }

    /**
     * Test IList
     */

    private void testList() {
        IList<Double> myList = new MyList<>(Double.class, 20);

        //test list size
        assert (myList.size() == 0);

        myList.add(2.0);
        //test size + get method
        assert (myList.size() == 1);
        try {
            assert (myList.get(0).equals(2.0));
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }

        myList.add(1.0);
        //test is empty
        assert (myList.size() == 2);
        assert (!myList.isEmpty());

        try {
            myList.remove(1);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        //test remove
        assert (myList.size() == 1);
    }


    /**
     * Test IStack
     */
    private void testStack() {
        IStack<String> myStack = new MyStack<>(String.class, 20);

        //test size + isEmpty method
        assert (myStack.isEmpty());
        assert (myStack.size() == 0);

        //test push + top
        final String text = "first push";
        myStack.push(text);
        assert (!myStack.isEmpty());
        assert (myStack.size() == 1);
        try {
            assert (myStack.top().equals(text));
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }

        //test pop
        String res = null;
        try {
            res = myStack.pop();
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
        assert res.equals(text);
        assert myStack.isEmpty();
        assert myStack.size() == 0;
    }
}
