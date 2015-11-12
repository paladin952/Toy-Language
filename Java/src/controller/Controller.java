package controller;

import Exceptions.*;
import interfaces.*;
import model.Collections.WrapperDictionary;
import model.Collections.WrapperList;
import model.Collections.WrapperStack;
import model.ProgramState;
import model.Statements.*;

/**
 * Created by Lucian on 10/11/2015.
 */
public class Controller {

    /**
     * Boolean: print current programe state if it is true
     */
    public static boolean PRINT_FLAG = true;

    /**
     * Repository object
     */
    private IRepository repository;

    /**
     * The constructor
     *
     * @param repository The repository
     */
    public Controller(IRepository repository) {
        this.repository = repository;

    }

    /**
     * Interface for printing on screen from ui
     */
    public interface PrintState {
        void print(String message);
    }

    /**
     * Listener for print
     */
    private PrintState printListener;

    /**
     * Creating a new program based on initial statement
     *
     * @param initialStatement initial IStatement
     */
    public void createProgram(IStatement initialStatement) {
        repository.createProgram(new WrapperStack<IStatement>(), new WrapperDictionary<String, Integer>(), new WrapperList<String>(), initialStatement);
    }

    /**
     * set listener for printing in ui
     *
     * @param listener The listener
     */
    public void setListener(PrintState listener) {
        printListener = listener;
    }

    /**
     * Run the program in debug mode, one step at a time
     *
     * @param programState The program
     * @throws StatementExecutionException
     */
    private void oneStep(ProgramState programState) throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        IStack<IStatement> myStack = programState.getExecutionStack();

        if (myStack.isEmpty())
            throw new StatementExecutionException();
        IStatement statement = myStack.pop();

        if (statement instanceof CompoundStatement) {
            CompoundStatement compoundStatement1 = (CompoundStatement) statement;
            myStack.push(compoundStatement1.getSecondStatement());
            myStack.push(compoundStatement1.getFirstStatement());
            return;
        }

        if (statement instanceof AssignStatement) {
            AssignStatement assignStatement = (AssignStatement) statement;
            Expression expression = assignStatement.getExpression();
            String id = assignStatement.getVariableName();
            IDictionary<String, Integer> myDictionary = programState.getMyDictionary();

            int val = expression.eval(myDictionary);
            //insert or update
            myDictionary.put(id, val);
            return;
        }

        if (statement instanceof PrintStatement) {
            IDictionary<String, Integer> myDictionary = programState.getMyDictionary();
            IList<String> output = programState.getOutput();
            PrintStatement printStatement = (PrintStatement) statement;
            Expression expr = printStatement.getExpression();
            output.add(String.valueOf(expr.eval(myDictionary)));
            return;
        }

        if (statement instanceof IfStatement) {
            IDictionary<String, Integer> myDictionary = programState.getMyDictionary();
            IfStatement ifStatement = (IfStatement) statement;
            if (ifStatement.getExpression().eval(myDictionary) != 0) {
                myStack.push(ifStatement.getThenStatement());
            } else {
                if(ifStatement.getElseStatement() != null){
                    myStack.push(ifStatement.getElseStatement());
                }
            }
            return;
        }

        if (statement instanceof WhileStatement) {
            IDictionary<String, Integer> myDictionary = programState.getMyDictionary();
            IList<String> output = programState.getOutput();
            WhileStatement whileStatement = (WhileStatement) statement;
            IStack<IStatement> secondStack = new WrapperStack<>();
            ProgramState secondProgramState = new ProgramState(secondStack, myDictionary, output, whileStatement.getStatement());
            while (whileStatement.getExpression().eval(myDictionary) != 0) {
                runAllSteps(secondProgramState);
                secondStack.push(whileStatement.getStatement());
            }
            return;
        }

        if(statement instanceof SkipStatement){
            return;
        }

        if(statement instanceof SwitchStatement){
            IDictionary<String, Integer> myDictionary = programState.getMyDictionary();
            SwitchStatement switchStatement = (SwitchStatement) statement;
            Expression expression = switchStatement.getExpression();
            if(switchStatement.getCase1().eval(myDictionary) == expression.eval(myDictionary)){
                myStack.push(switchStatement.getStatementCase1());
            }
            if(switchStatement.getCase2().eval(myDictionary) == expression.eval(myDictionary)){
                myStack.push(switchStatement.getStatementCase2());
            }
            myStack.push(switchStatement.getStatementDefault());
        }
    }

    /**
     * Run all program
     *
     * @throws StatementExecutionException
     */
    public void runAllSteps() throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        ProgramState programState = repository.getCurrentState();
        while (!programState.getExecutionStack().isEmpty()) {
            oneStep(programState);
            if (PRINT_FLAG) {
                printListener.print(programState.toString());
            }
        }
    }

    /**
     * Run all program
     *
     * @throws StatementExecutionException
     */
    private void runAllSteps(ProgramState programState) throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        while (!programState.getExecutionStack().isEmpty()) {
            oneStep(programState);
//            if (PRINT_FLAG) {
//                printListener.print(programState.toString());
//            }
        }
    }

    /**
     * Run step by step
     *
     * @throws StatementExecutionException
     */
    public void runOneStep() throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        ProgramState programState = repository.getCurrentState();
        if (programState.getExecutionStack().size() > 0) {
            oneStep(programState);
            if (PRINT_FLAG) {
                printListener.print(programState.toString());
            }
        }
    }


}
