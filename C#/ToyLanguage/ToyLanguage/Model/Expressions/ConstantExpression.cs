using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Expressions
{
    class ConstantExpression : IExpressions
    {
        /**
   * The number
   */
        private int number;

        /**
         * The constructor
         * @param number int number
         */
        public ConstantExpression(int number)
        {
            this.number = number;
        }

        /**
         * Evaluate the expression by return the number
         * @param table Table of values
         * @return int number
         */
      
    public int eval(IMyDictionary<string, int> table)
        {
            return number;
        }

        /**
         * String representation
         * @return String
         */
       
    public String toString()
        {
            return number.ToString();
        }

    }
}
