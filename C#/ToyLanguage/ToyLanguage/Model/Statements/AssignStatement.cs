using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    class AssignStatement : IStatement
    {
        /**
     * The name of the variable
     */
        private String variableName;

        /**
         * The expression to be assigned to variable
         */
        private IExpressions expression;

        /**
         * The constructor
         * @param variableName The name of variable
         * @param expression The expression
         */
        public AssignStatement(String variableName, IExpressions expression)
        {
            this.variableName = variableName;
            this.expression = expression;
        }

        /**
         * Getters and setters
         */
        public String getVariableName()
        {
            return variableName;
        }

        public void setVariableName(String variableName)
        {
            this.variableName = variableName;
        }

        public IExpressions getExpression()
        {
            return expression;
        }

        public void setExpression(IExpressions expression)
        {
            this.expression = expression;
        }

        /**
         * String representation
         * @return
         */
    
    public string ToString()
        {
            return variableName + "=" + expression.ToString();
        }
    }
}
