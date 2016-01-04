using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    class WriteHeapStatement : IMyStatement
    {

    /**
     * The variable name
     */
    private String variableName;

    /**
     * The expression
     */
    private IExpressions expression;

    /**
     * The constructor
     * @param variableName The var name
     * @param expression the expression
     */
    public WriteHeapStatement(String variableName, IExpressions expression)
    {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName()
    {
        return variableName;
    }

    public IExpressions getExpression()
    {
        return expression;
    }

    

    public string MyToString()
    {
        return "ReadHeap( " + variableName + " )";
    }
    }
}
