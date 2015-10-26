using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    class IfStatement :IStatement
    {
        /**
     * The expression to be evaluated
     */
        private IExpressions expression;

        /**
         * The statement to be called if expression is true
         */
        private IStatement thenStatement;

        /**
         * The statement to be called if expression is false
         */
        private IStatement elseStatement;

        internal IExpressions Expression
        {
            get
            {
                return expression;
            }

            set
            {
                expression = value;
            }
        }

        /**
         * The constructor
         * @param expression The expression
         * @param thenStatement The correct statement
         * @param elseStatement The false statement
         */
        public IfStatement(IExpressions expression, IStatement thenStatement, IStatement elseStatement)
        {
            this.Expression = expression;
            this.thenStatement = thenStatement;
            this.elseStatement = elseStatement;
        }

        /**
         * String representation
         * @return String
         */
    
    public String ToString()
        {
            return "IF(" + Expression.ToString() + ")THEN(" + thenStatement.ToString() + ")ELSE("
                    + elseStatement.ToString() + ")";
        }

        /**
         * Getters and setters
         */
        public IExpressions getExpression()
        {
            return Expression;
        }

        public void setExpression(IExpressions expression)
        {
            this.Expression = expression;
        }

        public IStatement getThenStatement()
        {
            return thenStatement;
        }

        public void setThenStatement(IStatement thenStatement)
        {
            this.thenStatement = thenStatement;
        }

        public IStatement getElseStatement()
        {
            return elseStatement;
        }

        public void setElseStatement(IStatement elseStatement)
        {
            this.elseStatement = elseStatement;
        }
    }
}
