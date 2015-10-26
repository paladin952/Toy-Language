using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToyLanguage.Interfaces
{
    interface IList<T>
    {
        /**
     * Add a new element in list
     *
     * @param element T element
     */
        void add(T element);

        /**
         * Get the element from list by position
         *
         * @param position Int position
         * @return T element
         */
        T get(int position);

        /**
         * Remove element T at position
         *
         * @param position Int position
         */
        void remove(int position);

        /**
         * Check if list is empty
         *
         * @return True if empty, false otherwise
         */
        bool isEmpty();

        /**
         * Get the size of the list
         *
         * @return Int size
         */
        int size();
    }
}
