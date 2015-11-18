using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    [Serializable]
    class WhileStatement : IMyStatement
    {

        IMyStatement statement;

        IExpressions expression;

        public WhileStatement(IMyStatement statement, IExpressions expression)
        {
            this.statement = statement;
            this.expression = expression;
        }

        public IMyStatement getStatement()
        {
            return statement;
        }

        public void setStatement(IMyStatement statement)
        {
            this.statement = statement;
        }

        public IExpressions getExpression()
        {
            return expression;
        }

        public void setExpression(IExpressions expression)
        {
            this.expression = expression;
        }

        public String MyToString()
        {
            return "While( " + expression.MyToString() + " )" + "{ " + statement.MyToString() + "\n}";
        }
    }
}
