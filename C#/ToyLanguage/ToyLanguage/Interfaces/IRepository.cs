using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Model;

namespace ToyLanguage.Interfaces
{
    interface IRepository
    {
        /**
     * @return The current Program's state
     */
        ProgramState getCurrentState();

        /**
         * Creates a new program
         *
         * @param mExecutionStack The execution stack of the program
         * @param myDictionary    The table of symbols for program
         * @param mOutput         The output list of the program
         * @param mInitialProgram The initial statement
         */
        void createProgram(IMyStack<IMyStatement> mExecutionStack, IMyDictionary<string, int> myDictionary, IMyList<string> mOutput, IMyStatement mInitialProgram);

    }
}
