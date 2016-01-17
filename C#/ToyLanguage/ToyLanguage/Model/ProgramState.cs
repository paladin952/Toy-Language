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

        /**
    * The state id
    */
        private int stateId;

        /**
         * The global state id
         */
        private static int globalStateId = 0;


        /**
    * The heap
    */
        private IHeap<int, int> heap;

        public ProgramState(IMyStack<IMyStatement> executionStack, IMyDictionary<string, int> myDictionary, IMyList<string> output, IHeap<int, int> heap, IMyStatement originalProgram)
        {
            this.executionStack = executionStack;
            this.myDictionary = myDictionary;
            this.output = output;
            this.originalProgram = originalProgram;
            this.executionStack.push(originalProgram);
            this.heap = heap;
            this.stateId = globalStateId++;
        }

        /**
    * @return True if executionStack is complete, false otherwise
    */
        public bool isNotCompleted()
        {
            return !executionStack.isEmpty();
        }

        /**
         * Run the program in debug mode, one step at a time
         *
         * @throws StatementExecutionException
         */
        public ProgramState oneStep() {
        if (executionStack.isEmpty())
            {
                return this;
            }
            
        IMyStatement statement = executionStack.pop();
        return statement.execute(this);
    }

    /**
     * String representations
     *
     * @return String
     */
    public String ToString()
        {
            StringBuilder result = new StringBuilder();
            result.Append("Id: " + stateId + Environment.NewLine);
            result.Append("Execution stack: " + Environment.NewLine);
            result.Append(executionStack.ToString());

            result.Append("HEAP: " + Environment.NewLine);
            result.Append(heap.ToString());

            result.Append("My dictionary: " + Environment.NewLine);
            result.Append("\n");
            result.Append(myDictionary.ToString());

            result.Append("Output: " + Environment.NewLine);
            result.Append(output.ToString());
            return result.ToString();
        }

        /**
         * Getters and setters
         */

        public int getStateId()
        {
            return stateId;
        }

        public IHeap<int, int> getHeap()
        {
            return heap;
        }

        public void setHeap(IHeap<int, int> heap)
        {
            this.heap = heap;
        }

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
