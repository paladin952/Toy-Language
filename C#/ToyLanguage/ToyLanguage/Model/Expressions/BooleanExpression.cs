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
    /**
     * Created by Lucian on 11/3/2015.
     */
    class BooleanExpression : IExpressions
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
        private string myOperator;

        public BooleanExpression(string myOperator, IExpressions expression1, IExpressions expression2) {
            this.operatorType = getOperatorType(myOperator);
            this.firstExpression = expression1;
            this.secondExpression = expression2;
        }

        /**
         * Getting the operator type from string
         * @param operator The String operator
         * @return OperatorsEnum
         */
        private OperatorsEnum getOperatorType(string myOperator) {
            this.myOperator = myOperator;
            switch (myOperator) {
                case "<":
                    return OperatorsEnum.LESS;
                case "<=":
                    return OperatorsEnum.LESS_EQUAL;
                case ">":
                    return OperatorsEnum.GRATER;
                case ">=":
                    return OperatorsEnum.GRATER_EQUAL;
                case "==":
                    return OperatorsEnum.EQUAL;
                default:
                    return OperatorsEnum.DIFFERENT;
            }
        }

        /**
         * Evaluates the expression
         * For simplicity the result will be converted to int
         * @param table The table of values in case of a variable
         * @return int result
         */

        public int eval(IMyDictionary<string, int> table) { 
            switch (operatorType)
            {
                case OperatorsEnum.LESS:
                    if (firstExpression.eval(table) < secondExpression.eval(table))
                    {
                        return 1;
                    }
                    return 0;
                case OperatorsEnum.LESS_EQUAL:
                    if (firstExpression.eval(table) <= secondExpression.eval(table))
                    {
                        return 1;
                    }
                    return 0;
                case OperatorsEnum.GRATER_EQUAL:
                    if (firstExpression.eval(table) >= secondExpression.eval(table))
                    {
                        return 1;
                    }
                    return 0;
                case OperatorsEnum.GRATER:
                    if (firstExpression.eval(table) > secondExpression.eval(table))
                    {
                        return 1;
                    }
                    return 0;
                case OperatorsEnum.EQUAL:
                    if (firstExpression.eval(table) == secondExpression.eval(table))
                    {
                        return 1;
                    }
                    return 0;
                case OperatorsEnum.DIFFERENT:
                    if (firstExpression.eval(table) != secondExpression.eval(table))
                    {
                        return 1;
                    }
                    return 0;
                default:
                    return int.MinValue;
            }
        }


        public String MyToString()
        {
            return "(" + firstExpression.MyToString() + " " + myOperator +" " + secondExpression.MyToString();
        }
    }

}
