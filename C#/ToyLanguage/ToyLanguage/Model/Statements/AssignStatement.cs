﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    [Serializable]
    class AssignStatement : IMyStatement
    {
        /**
     * The name of the variable
     */
        private String variableName;

        /**
         * The expression to be assigned to variable
         */
        private IExpressions expression;

        /**
         * The constructor
         * @param variableName The name of variable
         * @param expression The expression
         */
        public AssignStatement(String variableName, IExpressions expression)
        {
            this.variableName = variableName;
            this.expression = expression;
        }

        /**
         * Getters and setters
         */
        public String getVariableName()
        {
            return variableName;
        }

        public void setVariableName(String variableName)
        {
            this.variableName = variableName;
        }

        public IExpressions getExpression()
        {
            return expression;
        }

        public void setExpression(IExpressions expression)
        {
            this.expression = expression;
        }

        /**
         * String representation
         * @return
         */
    
    public string MyToString()
        {
            
            return variableName + "=" + expression.MyToString();
        }

        public ProgramState execute(ProgramState programState)
        {
           
            int val = expression.eval(programState.getMyDictionary(), programState.getHeap());
            //insert or update
            programState.getMyDictionary().put(variableName, val);
            return null;
        }
    }
}
