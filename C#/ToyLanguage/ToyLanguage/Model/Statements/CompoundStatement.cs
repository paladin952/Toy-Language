using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    [Serializable]
    class CompoundStatement:IMyStatement
    {
        /**
    * First statement
    */
        private IMyStatement firstStatement;

        /**
         * Second statement
         */
        private IMyStatement secondStatement;

        /**
         * The constructor
         * @param firstStatement The first statement
         * @param secondStatement The second statement
         */
        public CompoundStatement(IMyStatement firstStatement, IMyStatement secondStatement)
        {
            this.firstStatement = firstStatement;
            this.secondStatement = secondStatement;
        }

        /**
         * Getters and setters
         */
        public IMyStatement getFirstStatement()
        {
            return firstStatement;
        }

        public void setFirstStatement(IMyStatement firstStatement)
        {
            this.firstStatement = firstStatement;
        }

        public IMyStatement getSecondStatement()
        {
            return secondStatement;
        }

        public void setSecondStatement(IMyStatement secondStatement)
        {
            this.secondStatement = secondStatement;
        }

        /**
         * String representation
         */
 
    public string MyToString()
        {
            return firstStatement.MyToString() + ";" + secondStatement.MyToString();
        }

        public ProgramState execute(ProgramState programState)
        {
            programState.getExecutionStack().push(secondStatement);
            programState.getExecutionStack().push(firstStatement);
            return null;
        }
    }
}
