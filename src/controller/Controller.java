package controller;

import Exceptions.StatementExecutionException;
import interfaces.*;
import model.Collections.MyDictionary;
import model.Collections.MyList;
import model.Collections.MyStack;
import model.Expresions.ArithmeticExpression;
import model.Expresions.ConstantExpression;
import model.Expresions.Expression;
import model.Expresions.VariableExpression;
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

//        IStatement initialStatement = new CompoundStatement(new AssignStatement("b", new ConstantExpression(100)),
//                new AssignStatement("a", new ArithmeticExpression("+", new VariableExpression("b"), new ConstantExpression(1))));
//
//        IStatement secondStatement = new CompoundStatement(new AssignStatement("b", new ConstantExpression(100)),
//                new PrintStatement(new VariableExpression("b")));
//
//        IStatement thirdStatement = new CompoundStatement(new AssignStatement("b", new ArithmeticExpression("+", new ConstantExpression(10), new ConstantExpression(20))),
//                new CompoundStatement(new AssignStatement("a", new ArithmeticExpression("*", new VariableExpression("b"), new ConstantExpression(2))),
//                        new PrintStatement(new VariableExpression("a"))));
//        this.repository.createProgram(new MyStack<>(IStatement.class, 100), new MyDictionary<String, Integer>(100), new MyList<String>(String.class, 100), thirdStatement);

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
            myStack.push(compoundStatement1.getmSecondStatement());
            myStack.push(compoundStatement1.getmFirstStatement());
            return;
        }

        if (statement instanceof AssignStatement) {
            AssignStatement assignStatement = (AssignStatement) statement;
            Expression expression = assignStatement.getmExpression();
            String id = assignStatement.getmId();
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
