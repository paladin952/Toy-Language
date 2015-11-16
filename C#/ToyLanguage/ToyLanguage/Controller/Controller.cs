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

namespace ToyLanguage
{
    class Controller
    {
        /**
    * Boolean: print current programe state if it is true
    */
        public static bool PRINT_FLAG = true;

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
            repository.createProgram(new WrapperStack<IMyStatement>(), new WrapperDictionary<string, int>(), new WrapperList<string>(), initialStatement);
        }

    /**
     * Run the program in debug mode, one step at a time
     * @param programState The program
     * @throws StatementExecutionException
     */
    private void oneStep(ProgramState programState)
    {
        IMyStack<IMyStatement> myStack = repository.getCurrentState().getExecutionStack();

        if (myStack.isEmpty())
            throw new StatementExecutionException();
        IMyStatement statement = myStack.pop();

        if (statement.GetType() == typeof (CompoundStatement)) {
            CompoundStatement compoundStatement1 = (CompoundStatement)statement;
            myStack.push(compoundStatement1.getSecondStatement());
            myStack.push(compoundStatement1.getFirstStatement());
            return;
        }

        if (statement.GetType() == typeof (AssignStatement)) {
            AssignStatement assignStatement = (AssignStatement)statement;
            IExpressions expression = assignStatement.getExpression();
            String id = assignStatement.getVariableName();
            IMyDictionary<String, int> myDictionary = programState.getMyDictionary();

            int val = expression.eval(myDictionary);
            //insert or update
            myDictionary.put(id, val);
            return;
        }

        if (statement.GetType() == typeof (PrintStatement)) {
            IMyDictionary<String, int> myDictionary = programState.getMyDictionary();
            IMyList<String> output = programState.getOutput();
            PrintStatement printStatement = (PrintStatement)statement;
            IExpressions expr = printStatement.getExpression();
            output.add(expr.eval(myDictionary).ToString());
            return;
        }

        if (statement.GetType() == typeof(IfStatement)) {
            IMyDictionary<String, int> myDictionary = programState.getMyDictionary();
            IfStatement ifStatement = (IfStatement)statement;
            if (ifStatement.getExpression().eval(myDictionary) != 0)
            {
                myStack.push(ifStatement.getThenStatement());
            }
            else
            {
                myStack.push(ifStatement.getElseStatement());
            }
            return;
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
            if (PRINT_FLAG)
            {
               printListener.print(programState.ToString());
            }
        }
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
            if (PRINT_FLAG)
            {
               printListener.print(programState.ToString());
            }
        }
    }
}
}
