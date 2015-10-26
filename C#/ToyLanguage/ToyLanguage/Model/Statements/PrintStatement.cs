using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    class PrintStatement: IStatement
    {
        /**
     * The expression to be printed
     */
        private IExpressions expression;

        /**
         * The constructor
         * @param expression Expression
         */
        public PrintStatement(IExpressions expression)
        {
            this.expression = expression;
        }


        /**
         * Getters and setters
         */

        public IExpressions getExpression()
        {
            return expression;
        }

        public void setExpression(IExpressions mExpression)
        {
            this.expression = mExpression;
        }


        /**
         * String representation
         * @return String
         */

    public String toString()
        {
            return "Print(" + expression.ToString() + ")";

        }
    }
}
