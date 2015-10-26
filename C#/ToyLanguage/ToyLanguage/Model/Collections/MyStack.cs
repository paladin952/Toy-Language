using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Collections
{
    class MyStack<T> : IStack<T>
    {
        /**
    * The array
    */
        private T[] array;

        /**
         * The size of the stack
         */
        private int sizeOfArray;

        /**
         * The constructor
         *
         * @param cls      The class of T
         * @param capacity The max capacity
         */
        public MyStack(int capacity)
        {

            this.array = new T[capacity];
            this.sizeOfArray = 0;
        }

        /**
         * Get last element from stack
         * Delete it from stack
         *
         * @return T element
         */

    public T pop()
        {
            if (size() > 0)
            {
                T element = array[sizeOfArray - 1];
                sizeOfArray--;
                return element;
            }
            throw new System.ArgumentException("Empty stack");
        }

        /**
         * Push an element in top of the stack
         *
         * @param element The element
         */

    public void push(T element)
        {
            array[sizeOfArray] = element;
            sizeOfArray++;
        }

        /**
         * Get last element from stack
         *
         * @return T element
         */
 
    public T top()
        {
            return array[sizeOfArray - 1];
        }

        /**
         * Check if the stack is empty
         *
         * @return True if yes, false otherwise
         */

    public bool isEmpty()
        {
            return sizeOfArray <= 0;
        }

        /**
         * Get the size of the stack
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
     
    public String ToString()
        {
            StringBuilder result = new StringBuilder();
            for (int i = sizeOfArray - 1; i >= 0; i--)
            {
                result.Append(array[i].ToString());
                result.Append("\n");
            }
            return result.ToString();
        }
    }
}
