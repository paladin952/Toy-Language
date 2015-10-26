using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;

namespace ToyLanguage.Model
{
    class ProgramState
    {
        /**
     * The execution stack containing statements
     */
        private IStack<IStatement> executionStack;

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
        private IStatement originalProgram;

        public ProgramState(IStack<IStatement> executionStack, IMyDictionary<string, int> myDictionary, IMyList<string> output, IStatement originalProgram)
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

        public IStack<IStatement> getExecutionStack()
        {
            return executionStack;
        }

        public void setExecutionStack(IStack<IStatement> mExecutionStack)
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

        public IStatement getOriginalProgram()
        {
            return originalProgram;
        }

        public void setOriginalProgram(IStatement originalProgram)
        {
            this.originalProgram = originalProgram;
        }
    }
}
