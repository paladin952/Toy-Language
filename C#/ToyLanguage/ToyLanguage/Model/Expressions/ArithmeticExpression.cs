using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;
using ToyLanguage.Utils;

namespace ToyLanguage.Model.Expressions
{
    class ArithmeticExpression : IExpressions
    {
        /**
   * First expression
   */
        private IExpressions firstExpression;

        /**
         * Second expression
         */
        private IExpressions secondExpression;

        /**
         * The type of operator
         */
        private OperatorsEnum operatorType;

        /**
         * The operator as string
         */
        private string  theOperator;

    /**
     * The constructor
     * @param operatorType The type of the operator for current arithmetic expression
     * @param firstExpression The first expression
     * @param secondExpression The second expression
     */
    public ArithmeticExpression(string operatorType, IExpressions firstExpression, IExpressions secondExpression)
        {
            this.operatorType = getOperatorType(operatorType);
            this.firstExpression = firstExpression;
            this.secondExpression = secondExpression;
        }

        /**
         * Getting the operator type from string
         * @param operator The String operator
         * @return OperatorsEnum
         */
        private OperatorsEnum getOperatorType(string theOperator)
        {
            this.theOperator = theOperator;
            switch (theOperator) {
            case "+":
                return OperatorsEnum.ADD;
            case "-":
                return OperatorsEnum.SUBSTRACT;
            case "*":
                return OperatorsEnum.MULTIPLY;
                default:
                return OperatorsEnum.DIVIDE;
            }
        }

        /**
         * Evaluates the expression
         * For simplicity the result will be converted to int
         * @param table The table of values in case of a variable
         * @return int result
         */
 
    public int eval(IMyDictionary<string, int> table)
        {
            switch (operatorType)
            {
                case OperatorsEnum.ADD:
                    return (int)(firstExpression.eval(table) + secondExpression.eval(table));
                case OperatorsEnum.SUBSTRACT:
                    return (int)(firstExpression.eval(table) - secondExpression.eval(table));
                case OperatorsEnum.MULTIPLY:
                    return (int)(firstExpression.eval(table) * secondExpression.eval(table));
                case OperatorsEnum.DIVIDE:
                    return (int)(firstExpression.eval(table) + secondExpression.eval(table));
                default:
                    return int.MaxValue;
            }
        }

        /**
         * String representation
         * @return String
         */
       
    public String MyToString()
        {
            return firstExpression.ToString() + theOperator +secondExpression.ToString();
        }
    }
}
