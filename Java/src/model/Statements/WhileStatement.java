package model.Statements;

import Exceptions.*;
import interfaces.*;
import model.Collections.WrapperStack;
import model.ProgramState;
import utils.Constants;

import java.util.Stack;

/**
 * Created by Lucian on 11/10/2015.
 */
public class WhileStatement implements IStatement {

    IStatement statement;

    Expression expression;

    public WhileStatement(IStatement statement, Expression expression) {
        this.statement = statement;
        this.expression = expression;
    }

    public IStatement getStatement() {
        return statement;
    }

    public void setStatement(IStatement statement) {
        this.statement = statement;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "While( " + expression.toString() + " )" + "{ " + statement.toString() + "\n}";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws DivideByZeroException, ValueNotFoundException, StatementExecutionException, InvalidPositionException, EmptyStackException {
        //TODO think how to solve the coupling with the controller
        IHeap<Integer, Integer> heap = programState.getHeap();
        IDictionary<String, Integer> myDictionary = programState.getMyDictionary();
        IList<String> output = programState.getOutput();
        IStack<IStatement> secondStack = new WrapperStack<>();
        Stack<IDictionary<String, Integer>> stackSymbolTable = new Stack<>();
        stackSymbolTable.add(myDictionary);

        ProgramState secondProgramState = new ProgramState(secondStack, stackSymbolTable, output, heap, programState.getProceduresTable(), getStatement());
        while (getExpression().eval(myDictionary, heap) != 0) {
            runAllSteps(secondProgramState);
            secondStack.push(getStatement());
        }
        return null;
    }

    /**
     * Run all program
     *
     * @throws StatementExecutionException
     */
    private void runAllSteps(ProgramState programState) throws StatementExecutionException, EmptyStackException, ValueNotFoundException, InvalidPositionException, DivideByZeroException {
        while (!programState.getExecutionStack().isEmpty()) {
            oneStep(programState);
        }
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
        statement.execute(programState);
    }
}
