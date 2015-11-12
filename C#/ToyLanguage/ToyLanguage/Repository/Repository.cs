using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Exceptions;
using ToyLanguage.Interfaces;
using ToyLanguage.Model;

namespace ToyLanguage
{
    class Repository: IRepository
    {
        /**
    * The list of current program states
    * For now only one item will be there
    */
        private List<ProgramState> programStateList;

        /**
         * The constructor
         */
        public Repository()
        {
            programStateList = new List<ProgramState>();
        }

        public void createProgram(IMyStack<IMyStatement> mExecutionStack, IMyDictionary<String, int> myDictionary, IMyList<String> mOutput, IMyStatement mInitialProgram)
        {
            programStateList.Add(new ProgramState(mExecutionStack, myDictionary, mOutput, mInitialProgram));
        }

        /**
         * Get the latest program state
         * @return The ProgramState
         */
     
    public ProgramState getCurrentState()
        {
            if(programStateList.Count() == 0)
            {
                throw new EmptyContainerException();
            }
            return programStateList.ElementAt(programStateList.Count() - 1);
        }
    }
}
