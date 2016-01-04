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
        public static int PRINT_FLAG = Constants.NO_PRINT;

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

        /**
         * Run the program in debug mode, one step at a time
         * @param programState The program
         * @throws StatementExecutionException
         */
        private void oneStep(ProgramState programState)
        {
            IMyStack<IMyStatement> myStack = repository.getCurrentState().getExecutionStack();
            IMyDictionary<string, int> myDictionary = programState.getMyDictionary();
            IHeap<int, int> heap = programState.getHeap();
            if (myStack.isEmpty())
                throw new StatementExecutionException();
            IMyStatement statement = myStack.pop();

            if (statement.GetType() == typeof(CompoundStatement)) {
                CompoundStatement compoundStatement1 = (CompoundStatement)statement;
                myStack.push(compoundStatement1.getSecondStatement());
                myStack.push(compoundStatement1.getFirstStatement());
                return;
            }

            if (statement.GetType() == typeof(AssignStatement)) {
                AssignStatement assignStatement = (AssignStatement)statement;
                IExpressions expression = assignStatement.getExpression();
                String id = assignStatement.getVariableName();
 

                int val = expression.eval(myDictionary, heap);
                //insert or update
                myDictionary.put(id, val);
                return;
            }

            if (statement.GetType() == typeof(PrintStatement)) {
 
                IMyList<String> output = programState.getOutput();
                PrintStatement printStatement = (PrintStatement)statement;
                IExpressions expr = printStatement.getExpression();
                output.add(expr.eval(myDictionary, heap).ToString());
                return;
            }

            if (statement.GetType() == typeof(IfStatement)) {
                
                IfStatement ifStatement = (IfStatement)statement;
                if (ifStatement.getExpression().eval(myDictionary, heap) != 0)
                {
                    myStack.push(ifStatement.getThenStatement());
                }
                else
                {
                    myStack.push(ifStatement.getElseStatement());
                }
                return;
            }

            if (statement.GetType() ==  typeof(HeapAllocation)){
                HeapAllocation heapAllocation = (HeapAllocation)statement;
                int pointer = heap.size();
                heap.put(pointer, heapAllocation.getExpression().eval(myDictionary, heap));
                myDictionary.put(heapAllocation.getVariableName(), pointer);
            }

            if (statement.GetType() == typeof(WriteHeapStatement)){
                WriteHeapStatement writeHeapStatement = (WriteHeapStatement)statement;
                heap.put(myDictionary.lookUp(writeHeapStatement.getVariableName()), writeHeapStatement.getExpression().eval(myDictionary, heap));
            }
        }

        /**
         * Run all program
         * @throws StatementExecutionException
         */
        public void runAllSteps()
        {
            ProgramState programState = repository.getCurrentState();
            while (!programState.getExecutionStack().isEmpty()) {
                oneStep(programState);
                if (PRINT_FLAG == Constants.PRINT_CONSOLE)
                {
                    printListener.print(programState.ToString());
                }else if(PRINT_FLAG == Constants.PRINT_FILE)
                {
                    repository.SaveStateInFile();
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

    /**
     * Run step by step
     * @throws StatementExecutionException
     */
    public void runOneStep()
    {
        ProgramState programState = repository.getCurrentState();
        if(programState.getExecutionStack().size()>0){
            oneStep(programState);
                if (PRINT_FLAG == Constants.PRINT_CONSOLE)
                {
                    printListener.print(programState.ToString());
                }
                else if (PRINT_FLAG == Constants.PRINT_FILE)
                {
                    repository.SaveStateInFile();
                }
            }
    }
}
}
