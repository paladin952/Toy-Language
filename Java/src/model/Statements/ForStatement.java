package model.Statements;

import Exceptions.*;
import interfaces.*;
import model.ProgramState;

public class ForStatement implements IStatement {

    private Expression expression1;
    private Expression expression2;
    private AssignStatement changeStatement;
    private IStatement statement;

    public ForStatement(Expression expression1, Expression expression2, AssignStatement changeStatement, IStatement statement) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.changeStatement = changeStatement;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws DivideByZeroException, ValueNotFoundException, StatementExecutionException, InvalidPositionException, EmptyStackException {

        IHeap<Integer, Integer> heap = programState.getHeap();
        IDictionary<String, Integer> myDictionary = programState.getMyDictionary();
        IStack<IStatement> stack = programState.getExecutionStack();

        Expression v = expression1;
        while (v.eval(myDictionary, heap) < expression2.eval(myDictionary, heap)) {

            changeStatement.execute(programState);
            stack.push(statement);
        }

        return null;
    }

    @Override
    public String toString() {
        return "For(" + expression1.toString() + ";" + expression1.toString() + "<" + expression2.toString() + ";" + expression1.toString() + "=" + changeStatement.toString() + ")";
    }
}
