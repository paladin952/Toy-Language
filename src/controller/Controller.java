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
    public static boolean shouldPrint;
    private IRepository mRepository;

    public Controller(IRepository repository) {
        mRepository = repository;

        IStack<IStatement> stack = new MyStack<>(IStatement.class, 100);

        IStatement initialStatement = new CompoundStatement(new AssignStatement("b", new ConstantExpression(100)),
                new AssignStatement("a", new ArithmeticExpression("+", new VariableExpression("b"), new ConstantExpression(1))));

        IStatement secondStatement = new CompoundStatement(new AssignStatement("b", new ConstantExpression(100)),
                new PrintStatement(new VariableExpression("b")));

        IStatement thirdStatement = new CompoundStatement(new AssignStatement("b", new ArithmeticExpression("+", new ConstantExpression(10), new ConstantExpression(20))),
                new CompoundStatement(new AssignStatement("a", new ArithmeticExpression("*", new VariableExpression("b"), new ConstantExpression(2))),
                        new PrintStatement(new VariableExpression("a"))));
        mRepository.createProgram(stack, new MyDictionary<String, Integer>(100), new MyList<String>(String.class, 100), thirdStatement);

    }

    private void oneStep(ProgramState programState) throws StatementExecutionException {
        IStack<IStatement> myStack = mRepository.getCurrentState().getExecutionStack();

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
            Expression expr = printStatement.getmExpression();
            output.add(String.valueOf(expr.eval(myDictionary)));
            return;
        }

        if (statement instanceof IfStatement) {
            IDictionary<String, Integer> myDictionary = programState.getMyDictionary();
            IfStatement ifStatement = (IfStatement) statement;
            if (ifStatement.getmExpression().eval(myDictionary) != 0) {
                myStack.push(ifStatement.getmThenStatement());
            } else {
                myStack.push(ifStatement.getmElseStatement());
            }
            return;
        }
    }

    public void runAllSteps() throws StatementExecutionException {
        ProgramState programState = mRepository.getCurrentState();
        while (!programState.getExecutionStack().isEmpty()) {
            oneStep(programState);
            if(shouldPrint){
                System.out.println(programState.toString());
            }
        }
    }

    public void runOneStep() throws StatementExecutionException {
        ProgramState programState = mRepository.getCurrentState();
        if(programState.getExecutionStack().size()>0){
            oneStep(programState);
            if(shouldPrint){
                System.out.println(programState.toString());
            }
        }
    }

}
