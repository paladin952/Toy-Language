using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Statements
{
    class HeapAllocation : IMyStatement
    {

        /**
         * The varoiable name
         */
        private String variableName;

        private IExpressions expression;

        /**
         * Private contructor, use builder
         */
        public HeapAllocation(String variableName, IExpressions expression)
        {
            this.variableName = variableName;
            //        this.expressionResult = expression.eval(symbolicTable);
            this.expression = expression;
            //        pointer = heap.size();
            //        heap.put(heap.size() ,expressionResult);
            //        symbolicTable.put(variableName, pointer);
        }


        public String getVariableName()
        {
            return variableName;
        }

        public IExpressions getExpression()
        {
            return expression;
        }


        public string MyToString()
        {
            return "HeapAllocation{ " + variableName + " -> " + expression.MyToString() + " }";
        }

        public ProgramState execute(ProgramState programState)
        {
            int pointer = programState.getHeap().size() + 1;
            programState.getHeap().put(pointer,expression.eval(programState.getMyDictionary(), programState.getHeap()));
            programState.getMyDictionary().put(variableName, pointer);
            return null;
        }
    }
}
