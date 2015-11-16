using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Expressions
{
    class VariableExpression: IExpressions
    {
        /**
     * The name of the variable
     */
        private string name;

        /**
         * Constructor
         * @param name The id of variable
         */
        public VariableExpression(string name)
        {
            this.name = name;
        }

        /**
         * Evaluate the variable by getting its value from the Table
         * @param table Table of values
         * @return int value
         */
   
    public int eval(IMyDictionary<string, int> table)
        {
            return table.lookUp(name);
        }

        /**
         * String representation
         * @return String
         */
    
    public String MyToString()
        {
            return name;
        }
    }
}
