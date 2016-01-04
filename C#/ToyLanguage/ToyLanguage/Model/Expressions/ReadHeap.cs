using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model.Expressions
{
    class ReadHeap : IExpressions
    {

    /**
     * Variable name
     */
    private string variableName;
        
    /**
     * The constructor
     * @param variableName The variable name
     */
    public ReadHeap(string variableName)
    {
        this.variableName = variableName;
    }

        public int eval(IMyDictionary<string, int> table)
        {
            throw new NotImplementedException();
        }

        public int eval(IMyDictionary<string, int> table, IHeap<int, int> heap){
        return heap.lookUp(table.lookUp(variableName));
        }

        public string MyToString()
        {
            return "ReadHeap( " + variableName + " )";
        }

    }

}
