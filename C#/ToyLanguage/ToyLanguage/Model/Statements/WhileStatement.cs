using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;
using ToyLanguage.Model.Collections;

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
            return "While( " + expression.MyToString() + " )" + "{ " + statement.MyToString() + Environment.NewLine + "}";
        }

        public ProgramState execute(ProgramState programState)
        {
            IHeap<int, int> heap = programState.getHeap();
            IMyDictionary<String, int> myDictionary = programState.getMyDictionary();
            IMyList<String> output = programState.getOutput();
            IMyStack<IMyStatement> secondStack = new WrapperStack<IMyStatement>();
            ProgramState secondProgramState = new ProgramState(secondStack, myDictionary, output, heap, getStatement());
            while (getExpression().eval(myDictionary, heap) != 0)
            {
                runAllSteps(secondProgramState);
                secondStack.push(getStatement());
            }
            return null;
        }

        /**
    * Run all program
    *
    * @throws StatementExecutionException
    */
        private void runAllSteps(ProgramState programState)
        {
            while (!programState.getExecutionStack().isEmpty())
            {
                oneStep(programState);
            }
        }

        /**
         * Run the program in debug mode, one step at a time
         *
         * @param programState The program
         * @throws StatementExecutionException
         */
        private void oneStep(ProgramState programState)
        {
            IMyStack<IMyStatement> myStack = programState.getExecutionStack();
            if (myStack.isEmpty())
                throw new InsufficientExecutionStackException();
            IMyStatement statement = myStack.pop();
            statement.execute(programState);
        }
    }
}
