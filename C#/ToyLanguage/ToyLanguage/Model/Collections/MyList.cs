using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Collections
{
    [Serializable]
    class MyList<T> : IMyList<T>
    {
        /**
    * The array
    */
        private T[] array;

        /**
         * The size of the list
         */
        private int sizeOfArray;

        /**
         * Constructor
         *
         * @param cls      the class of T
         * @param capacity The capacity
         */
        public MyList(int capacity)
        {
            
            T[] a = new T[capacity];
            this.array = a;
            this.sizeOfArray = 0;
        }

        /**
         * Add an element in list
         *
         * @param element T element
         */
        
    public void add(T element)
        {
            array[sizeOfArray] = element;
            sizeOfArray++;
        }

        /**
         * Get element from list by position
         *
         * @param position Int position
         * @return The element
         */
  
    public T get(int position)
        {
            return array[position];
        }

        /**
         * Remove element from list by position
         *
         * @param position Int position
         */
      
    public void remove(int position)
        {
            for (int i = position; i < sizeOfArray - 1; i++)
            {
                array[i] = array[i + 1];
            }
            sizeOfArray--;
        }

        /**
         * Check if list is empty
         *
         * @return True if yes, false otherwise
         */
     
    public bool isEmpty()
        {
            return sizeOfArray <= 0;
        }

        /**
         * Get the size of the list
         *
         * @return int size
         */

    public int size()
        {
            return sizeOfArray;
        }

        /**
         * String representation
         *
         * @return String
         */

    public string ToString()
        {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < sizeOfArray; i++)
            {
                result.Append(array[i].ToString() + "\n");
            }
            return result.ToString();
        }
    }
}
