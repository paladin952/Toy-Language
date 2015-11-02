using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    class IfStatement :IMyStatement
    {
        /**
     * The expression to be evaluated
     */
        private IExpressions expression;

        /**
         * The statement to be called if expression is true
         */
        private IMyStatement thenStatement;

        /**
         * The statement to be called if expression is false
         */
        private IMyStatement elseStatement;

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
        public IfStatement(IExpressions expression, IMyStatement thenStatement, IMyStatement elseStatement)
        {
            this.Expression = expression;
            this.thenStatement = thenStatement;
            this.elseStatement = elseStatement;
        }

        /**
         * String representation
         * @return String
         */
    
    public string MyToString()
        {
            return "IF(" + Expression.MyToString() + ")THEN(" + thenStatement.MyToString() + ")ELSE("
                    + elseStatement.MyToString() + ")";
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

        public IMyStatement getThenStatement()
        {
            return thenStatement;
        }

        public void setThenStatement(IMyStatement thenStatement)
        {
            this.thenStatement = thenStatement;
        }

        public IMyStatement getElseStatement()
        {
            return elseStatement;
        }

        public void setElseStatement(IMyStatement elseStatement)
        {
            this.elseStatement = elseStatement;
        }
    }
}
