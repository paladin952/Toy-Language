using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToyLanguage.Exceptions
{
    class EmptyContainerException : Exception
    {
        public EmptyContainerException(string message)
      : base(message)
        {
        }

        public EmptyContainerException()
                : base("The container is empty!")
        {
        }
    }
}
