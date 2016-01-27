using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Exceptions;
using ToyLanguage.Interfaces;
using ToyLanguage.Model;
using ToyLanguage.Model.Collections;
using ToyLanguage.Model.Statements;
using ToyLanguage.Utils;

namespace ToyLanguage
{
    class Controller
    {
        /**
    * Boolean: print current programe state if it is true
    */
        public static int PRINT_FLAG = Constants.PRINT_CONSOLE;

        /**
         * Repository object
         */
        private IRepository repository;

        /**
    * Interface for printing on screen from ui
    */
        public interface PrintState
        {
            void print(string message);
        }

        /**
     * Listener for print
     */
        private PrintState printListener;

        /**
        Set listener to print in ui
            */
        public void setListener(PrintState listener)
        {
            printListener = listener;
        }

        /**
         * The constructor
         * @param repository The repository
         */
        public Controller(IRepository repository)
        {
            this.repository = repository;
        }

        /**
    * Creating a new program based on initial statement
    * @param initialStatement initial IStatement
    */
        public void createProgram(IMyStatement initialStatement)
        {
            repository.createProgram(new WrapperStack<IMyStatement>(), new WrapperDictionary<string, int>(), new WrapperList<string>(), new MyHeap<int, int>(), initialStatement);
        }

        public List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList)
        {
            return inPrgList.Where(p => p.isNotCompleted()).ToList();
        }

        public void oneStepForAll()
        {
            List<ProgramState> prgList = repository.getProgramStateList();
            List<Task<ProgramState>> taskList =
                (from prg in prgList
                 select Task<ProgramState>.Factory.StartNew(() => prg.oneStep())).ToList();

            List<ProgramState> newPrgList = (from tsk in taskList
                                         where tsk.Result != null
                                         select tsk.Result).ToList();

            newPrgList.AddRange(prgList.Where(p => !newPrgList.Any(q => q.getStateId() == p.getStateId())).ToList());
            repository.setProgramStateList(newPrgList);


            if (PRINT_FLAG == Constants.PRINT_CONSOLE)
            {
                foreach (ProgramState p in prgList)
                    printListener.print(p.ToString());
                repository.SaveStateInFile();

            }
            else if (PRINT_FLAG == Constants.PRINT_FILE)
            {
                repository.SaveStateInFile();
            }
        }



        /**
         * Run the program in debug mode, one step at a time
         * @param programState The program
         * @throws StatementExecutionException
         */
        private void oneStep(ProgramState programState)
        {
            List<ProgramState> prgList = removeCompletedPrg(repository.getProgramStateList());
            if (prgList.Count == 0)
            {
                return;
            }
            else {
                oneStepForAll();
            }

        }

        /**
         * Run all program
         * @throws StatementExecutionException
         */
        public void runAllSteps()
        {
           
            while (true)
            {
                List<ProgramState> prgList = removeCompletedPrg(repository.getProgramStateList());
                if (prgList.Count == 0)
                {
                    return;
                }
                else {
                    oneStepForAll();
                }
            }
        }

        public void Serialize()
        {
            repository.Serialize();
        }

        public void DeSerialize()
        {
            repository.DeSerialize();
        }
 
}
}
