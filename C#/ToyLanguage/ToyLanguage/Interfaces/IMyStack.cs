using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToyLanguage.Interfaces
{
    interface IMyStack<T>
    {
        /**
     * Get the top element and decrement the stack
     *
     * @return T element
     */
        T pop();

        /**
         * Push element T at the top of the stack
         *
         * @param element The element
         */
        void push(T element);

        /**
         * Get the top element from the stack
         *
         * @return T element
         */
        T top();

        /**
         * Check if the stack is empty
         *
         * @return True if yes, false otherwise
         */
        bool isEmpty();

        /**
         * Get the size of the stack
         *
         * @return Int size
         */
        int size();

        string ToString();
    }
}
