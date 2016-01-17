using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Collections
{
    [Serializable]
    class MyDictionary<K, V> : IMyDictionary<K, V>
    {
        /**
    * An array of tuple (key, value)
    */
        private Tuple[] array;

        /**
         * The size of the dictionary
         */
        private int sizeOfArray;

        /**
         * Inner class Tuple
         */
        private class Tuple
        {

            /**
             * The key
             */
            public K key;

            /**
             * The value
             */
            public V value;

            /**
             * The constructor
             *
             * @param key   The key
             * @param value The value
             */
            public Tuple(K key, V value)
            {
                this.key = key;
                this.value = value;
            }

            /**
             * Get the key
             *
             * @return Key
             */
            public K getKey()
            {
                return key;
            }

            /**
             * Set the key
             *
             * @param key Key
             */
            public void setKey(K key)
            {
                this.key = key;
            }

            /**
             * Get the value of current key
             *
             * @return Value
             */
            public V getValue()
            {
                return value;
            }

            /**
             * Set the value of the current key
             *
             * @param value Value
             */
            public void setValue(V value)
            {
                this.value = value;
            }
        }

        /**
         * Constructor
         *
         * @param size int max size of dictionary
         */
        public MyDictionary(int cap)
        {
            sizeOfArray = 0;
            Object[] tmp = new Object[cap];
            array = new Tuple[cap];
           
         }

    /**
     * Get the value of the id
     *
     * @param id The id
     * @return Value
     */
    public V lookUp(K id)
    {
        for (int i = 0; i < sizeOfArray; i++)
        {
            if (array[i].getKey().Equals(id))
            {
                return array[i].getValue();
            }
        }

       //if the key is not here return null
        throw new System.ArgumentException("Key not in table", "original");
    }

    /**
     * Check if dictionary is empty
     *
     * @return True if yes, false otherwise
     */
    public bool isEmpty()
    {
        return sizeOfArray <= 0;
    }

    /**
     * Get the size of the dictionary
     *
     * @return int size
     */
    public int size()
    {
        return sizeOfArray;
    }

    /**
     * Check if K key is in dictionary
     *
     * @param key The key
     * @return True if yes, false otherwise
     */

    public bool containsKey(K key)
    {
        for (int i = 0; i < sizeOfArray; i++)
        {
            if (array[i].getKey().Equals(key))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Put the V value in K key
     *
     * @param key   The key
     * @param value The value
     */
 
    public void put(K key, V value)
    {
        bool found = false;
        for (int i = 0; i < sizeOfArray; i++)
        {
            if (array[i].getKey().Equals(key))
            {
                array[i].setValue(value);
                found = true;
            }
        }

        if (!found)
        {
            array[sizeOfArray] = new Tuple(key, value);
            sizeOfArray++;
        }
    }

    /**
     * Remove the key from dictionary
     *
     * @param key The key
     */
    public void remove(K key)
    {
        for (int i = 0; i < sizeOfArray; i++)
        {
            if (array[i].getKey().Equals(key))
            {
                for (int j = i; j < sizeOfArray - 1; j++)
                {
                    array[j] = array[j + 1];
                }
                sizeOfArray--;
                break;
            }
        }
    }

    /**
     * String representation
     *
     * @return String
     *
     */
    public string ToString()
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sizeOfArray; i++)
        {
            result.Append(array[i].key + "->" + array[i].value + "\n");
        }
        return result.ToString();
    }

        public void addAll(Dictionary<K, V> all)
        {
            throw new NotImplementedException();
        }

        public Dictionary<K, V> getMap()
        {
            throw new NotImplementedException();
        }
    }
}
