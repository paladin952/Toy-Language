using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    [Serializable]
    class SwitchStatement : IMyStatement
    {

        private IExpressions expression;
        private IExpressions case1;
        private IExpressions case2;

        private IMyStatement statementDefault;
        private IMyStatement statementCase1;
        private IMyStatement statementCase2;

        public SwitchStatement(IExpressions expression, IExpressions case1, IExpressions case2,
                           IMyStatement statementDefault, IMyStatement statementCase1, IMyStatement statementCase2)
        {

            this.expression = expression;
            this.case1 = case1;
            this.case2 = case2;
            this.statementDefault = statementDefault;
            this.statementCase1 = statementCase1;
            this.statementCase2 = statementCase2;
        }

        public String MyToString()
        {
            return "switch(" + expression.MyToString() + ")"+ Environment.NewLine +"{"
                    + "case " + case1.MyToString() + Environment.NewLine + statementCase1.MyToString()
                    + Environment.NewLine+"case " + case2.MyToString() + Environment.NewLine + statementCase2.MyToString()
                    + Environment.NewLine+"default " + statementDefault.MyToString();
        }

        public IExpressions getExpression()
        {
            return expression;
        }

        public IExpressions getCase1()
        {
            return case1;
        }

        public IExpressions getCase2()
        {
            return case2;
        }

        public IMyStatement getStatementDefault()
        {
            return statementDefault;
        }

        public IMyStatement getStatementCase1()
        {
            return statementCase1;
        }

        public IMyStatement getStatementCase2()
        {
            return statementCase2;
        }

        public ProgramState execute(ProgramState programState)
        {
            throw new NotImplementedException();
        }
    }
}