using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model
{
    [Serializable]
    class ProgramState
    {
        /**
     * The execution stack containing statements
     */
        private IMyStack<IMyStatement> executionStack;

        /**
         * The table of variables as a hash map
         */
        private IMyDictionary<string, int> myDictionary;

        /**
         * List of output
         * The messages are coming from print statement
         */
        private IMyList<string> output;

        /**
         * A copy of the original program
         */
        private IMyStatement originalProgram;

        public ProgramState(IMyStack<IMyStatement> executionStack, IMyDictionary<string, int> myDictionary, IMyList<string> output, IMyStatement originalProgram)
        {
            this.executionStack = executionStack;
            this.myDictionary = myDictionary;
            this.output = output;
            this.originalProgram = originalProgram;
            this.executionStack.push(originalProgram);
        }

        /**
         * String representation
         *
         * @return String
         */
    public String ToString()
        {
            StringBuilder result = new StringBuilder();
        
            result.Append("Execution stack: \n");
            result.Append(executionStack.ToString());

            result.Append("My dictionary: \n");
            result.Append("\n");
            result.Append(myDictionary.ToString());

            result.Append("Output: \n");
            result.Append(output.ToString());
            return result.ToString();
        }

        /**
         * Getters and setters
         */

        public IMyStack<IMyStatement> getExecutionStack()
        {
            return executionStack;
        }

        public void setExecutionStack(IMyStack<IMyStatement> mExecutionStack)
        {
            this.executionStack = mExecutionStack;
        }

        public IMyDictionary<string, int> getMyDictionary()
        {
            return myDictionary;
        }

        public void setMyDictionary(IMyDictionary<string, int> myDictionary)
        {
            this.myDictionary = myDictionary;
        }

        public IMyList<string> getOutput()
        {
            return output;
        }

        public void setOutput(IMyList<String> output)
        {
            this.output = output;
        }

        public IMyStatement getOriginalProgram()
        {
            return originalProgram;
        }

        public void setOriginalProgram(IMyStatement originalProgram)
        {
            this.originalProgram = originalProgram;
        }
    }
}
