using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Exceptions;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Collections
{
    class WrapperList<T> : IMyList<T>
    {
        private List<T> data;
            
        public WrapperList()
        {
            data = new List<T>();
        }

        public void add(T element)
        {
            data.Add(element);
        }

        public T get(int position)
        {
            if (position < 0 || position >= data.Count)
            {
                throw new InvalidPositionException();
            }
            return data.ElementAt(position);
        }

        public bool isEmpty()
        {
            if(data.Count == 0)
            {
                return true;
            }
            return false;
        }

        public void remove(int position)
        {
            if(position < 0 || position >= data.Count)
            {
                throw new InvalidPositionException();
            }
            data.RemoveAt(position);
        }

        public int size()
        {
            return data.Count;
        }

        public string ToString()
        {
            StringBuilder result = new StringBuilder();
            foreach (T element in data){
                result.Append(element.ToString() + "\n");
            }
            return result.ToString();
        }
    }
}

