using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;
using ToyLanguage.Utils;

namespace ToyLanguage.Model.Expressions
{
    [Serializable]
    class LogicExpression : IExpressions
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
        private String theOperator;

        public LogicExpression(String theOperator, IExpressions expression1, IExpressions expression2) {
            this.operatorType = getOperatorType(theOperator);
            this.firstExpression = expression1;
            this.secondExpression = expression2;
        }

        /**
         * Getting the operator type from string
         *
         * @param operator The String operator
         * @return OperatorsEnum
         */
        private OperatorsEnum getOperatorType(String theOperator) {
            this.theOperator = theOperator;
            switch (theOperator) {
            case "&&":
                return OperatorsEnum.AND;
            case "||":
                return OperatorsEnum.OR;
                default:
                return OperatorsEnum.NOT;
            }
        }

        /**
         * Evaluates the expression
         * For simplicity the result will be converted to int
         *
         * @param table The table of values in case of a variable
         * @return int result
         */
     public int eval(IMyDictionary<string, int> table)
        {

            bool firstBoolean;
            bool secondBoolean;

            if (firstExpression.eval(table) == 0)
            {
                firstBoolean = false;
            }
            else
            {
                firstBoolean = true;
            }

            if (firstExpression.eval(table) == 0)
            {
                secondBoolean = false;
            }
            else
            {
                secondBoolean = true;
            }

            switch (operatorType)
            {
                case OperatorsEnum.AND:
                    if (firstBoolean && secondBoolean)
                    {
                        return 1;
                    }
                    return 0;
                case OperatorsEnum.OR:
                    if (firstBoolean || secondBoolean)
                    {
                        return 1;
                    }
                    return 0;
                case OperatorsEnum.NOT:
                    if (!firstBoolean)
                    {
                        return 1;
                    }
                    return 0;
                default:
                    return int.MinValue;
            }
        }

      
     public String MyToString() {
            return "(" + firstExpression.MyToString() + " " + theOperator +" " + secondExpression.MyToString() + ")";
        }

    }
}
