package tests;

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
        assert myDictionary.lookUp("key1").equals(1.0);

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
        assert (myList.get(0).equals(2.0));

        myList.add(1.0);
        //test is empty
        assert (myList.size() == 2);
        assert (!myList.isEmpty());

        myList.remove(1);
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
        assert (myStack.top().equals(text));

        //test pop
        String res = myStack.pop();
        assert res.equals(text);
        assert myStack.isEmpty();
        assert myStack.size() == 0;
    }
}
