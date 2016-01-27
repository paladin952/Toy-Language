using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;
using ToyLanguage.Model.Expressions;

namespace ToyLanguage.Model.Statements
{
    class SleepStatement : IMyStatement
    {
        private IExpressions time;

        public SleepStatement(IExpressions time)
        {
            this.time = time;
        }
        
        public ProgramState execute(ProgramState programState)
        {
            //IMyStatement statement = programState.getExecutionStack().pop();
            int seconds = time.eval(programState.getMyDictionary(), programState.getHeap());
            if (seconds != 0)
            {
                programState.getExecutionStack().push(new SleepStatement(new ConstantExpression(seconds - 1)));
            }
            return null;
        }

        public string MyToString()
        {
            return "Sleep(" +  time.MyToString() +")";
        }
    }
}
