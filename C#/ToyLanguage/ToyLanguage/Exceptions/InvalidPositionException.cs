using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToyLanguage.Exceptions
{
    class InvalidPositionException : Exception
    {
        public InvalidPositionException(string message)
      : base(message)
        {
        }

        public InvalidPositionException()
                : base("The position is not valid!")
        {
        }
    }
}
