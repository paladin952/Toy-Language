using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToyLanguage.Interfaces
{
    interface IMyDictionary<K, V>
    {
           
         /** Get the value at id K
         *
         * @param id The id
         * @return The value V of id K
         */
        V lookUp(K id);

        /**
         * Check if dictionary is empty
         *
         * @return True if yes, false otherwise
         */
        bool isEmpty();

        /**
         * Get the size of the dictionary
         *
         * @return Int size
         */
        int size();

        /**
         * Check if key K is in dictioinary
         *
         * @param key The key
         * @return true if yes, false otherwise
         */
        bool containsKey(K key);

        /**
         * Put value V in key K if exists key
         * Create a new key and  a new value if key doesn't exists
         *
         * @param key   The key
         * @param value The value
         */
        void put(K key, V value);

        /**
         * Remove from dictionary the structure at key
         *
         * @param key The key
         */
        void remove(K key);

        /**
         * String representation
         *
         * @return string
         */
        string ToString();
    }
}
