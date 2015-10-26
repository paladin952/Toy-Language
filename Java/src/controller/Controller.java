package controller;

import Exceptions.StatementExecutionException;
import interfaces.*;
import model.Collections.MyDictionary;
import model.Collections.MyList;
import model.Collections.MyStack;
import interfaces.Expression;
import model.ProgramState;
import model.Statements.AssignStatement;
import model.Statements.CompoundStatement;
import model.Statements.IfStatement;
import model.Statements.PrintStatement;

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
     * @param repository The repository
     */
    public Controller(IRepository repository) {
        this.repository = repository;

    }

    /**
     * Creating a new program based on initial statement
     * @param initialStatement initial IStatement
     */
    public void createProgram(IStatement initialStatement){
        repository.createProgram(new MyStack<>(IStatement.class, 100), new MyDictionary<String, Integer>(100), new MyList<String>(String.class, 100), initialStatement);
    }

    /**
     * Run the program in debug mode, one step at a time
     * @param programState The program
     * @throws StatementExecutionException
     */
    private void oneStep(ProgramState programState) throws StatementExecutionException {
        IStack<IStatement> myStack = repository.getCurrentState().getExecutionStack();

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
                myStack.push(ifStatement.getElseStatement());
            }
            return;
        }
    }

    /**
     * Run all program
     * @throws StatementExecutionException
     */
    public void runAllSteps() throws StatementExecutionException {
        ProgramState programState = repository.getCurrentState();
        while (!programState.getExecutionStack().isEmpty()) {
            oneStep(programState);
            if(PRINT_FLAG){
                System.out.println(programState.toString());
            }
        }
    }

    /**
     * Run step by step
     * @throws StatementExecutionException
     */
    public void runOneStep() throws StatementExecutionException {
        ProgramState programState = repository.getCurrentState();
        if(programState.getExecutionStack().size()>0){
            oneStep(programState);
            if(PRINT_FLAG){
                System.out.println(programState.toString());
            }
        }
    }

}
