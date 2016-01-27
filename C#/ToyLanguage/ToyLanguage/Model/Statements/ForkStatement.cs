using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;
using ToyLanguage.Model.Collections;

namespace ToyLanguage.Model.Statements
{
    
    class ForkStatement : IMyStatement
    {

        private IMyStatement statement;
        
        public ForkStatement(IMyStatement statement)
        {
            this.statement = statement;
        }

        public ProgramState execute(ProgramState programState)
        {
            IMyStack<IMyStatement> stack = new WrapperStack<IMyStatement>();
            stack.push(statement);

            IMyDictionary<String, int> copyDictionary = new WrapperDictionary<String, int>();

            copyDictionary.addAll(programState.getMyDictionary().getMap());
            return new ProgramState(stack, copyDictionary, programState.getOutput(), programState.getHeap(), statement);
        }

        public string MyToString()
        {
            return "Fork(" + statement.MyToString() + ")";
        }
    }
}
