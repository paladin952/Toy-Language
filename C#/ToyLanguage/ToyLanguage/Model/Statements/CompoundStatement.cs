using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    class CompoundStatement:IStatement
    {
        /**
    * First statement
    */
        private IStatement firstStatement;

        /**
         * Second statement
         */
        private IStatement secondStatement;

        /**
         * The constructor
         * @param firstStatement The first statement
         * @param secondStatement The second statement
         */
        public CompoundStatement(IStatement firstStatement, IStatement secondStatement)
        {
            this.firstStatement = firstStatement;
            this.secondStatement = secondStatement;
        }

        /**
         * Getters and setters
         */
        public IStatement getFirstStatement()
        {
            return firstStatement;
        }

        public void setFirstStatement(IStatement firstStatement)
        {
            this.firstStatement = firstStatement;
        }

        public IStatement getSecondStatement()
        {
            return secondStatement;
        }

        public void setSecondStatement(IStatement secondStatement)
        {
            this.secondStatement = secondStatement;
        }

        /**
         * String representation
         */
 
    public string ToString()
        {
            return firstStatement.ToString() + ";" + secondStatement.ToString();
        }
    }
}
