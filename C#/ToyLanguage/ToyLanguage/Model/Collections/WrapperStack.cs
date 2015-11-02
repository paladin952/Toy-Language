using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Collections
{
    class WrapperStack<T> : IMyStack<T> where T : IMyStatement
    {
        private Stack<T> stack;
           
        public WrapperStack()
        {
            stack = new Stack<T>();
        }

        public bool isEmpty()
        {
            if(stack.Count == 0)
            {
                return true;
            }
            return false;
        }

        public T pop()
        {
            return stack.Pop();
        }

        public void push(T element)
        {
            stack.Push(element);
        }

        public int size()
        {
            return stack.Count;
        }

        public T top()
        {
            return stack.Peek();
        }

        public string ToString()
        {
            StringBuilder result = new StringBuilder();

            foreach(T element in stack){
                result.Append(element.MyToString());
                result.Append("\n");
               
            }
            return result.ToString();
        }
    }

}

