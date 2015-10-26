using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ToyLanguage.Exceptions
{
    class StatementExecutionException: Exception
    {
        public StatementExecutionException(string message)
      : base(message)
        {
        }

        public StatementExecutionException()
                : base("The are no statement to execute")
        {
        }
    }
}
