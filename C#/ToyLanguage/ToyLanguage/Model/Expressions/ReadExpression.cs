using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Expressions
{
    [Serializable]
    class ReadExpression : IExpressions
    {

        /**
         * The number
         */
        private int number;


        /**
         * The constructor
         *
         */
        public ReadExpression() {
 
            Console.WriteLine("\nENTER YOUR NUMBER: ");
            number = Int32.Parse(Console.ReadLine());
        }

        public int eval(IMyDictionary<string, int> table)
        {
            return number;
        }

      
        /**
         * String representation
         *
         * @return String
         */
     public String MyToString() {
            return "Read(" + number.ToString() + ")";

        }
    }
}
