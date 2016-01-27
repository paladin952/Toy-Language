package model.Statements;

import Exceptions.*;
import interfaces.Expression;
import interfaces.IDictionary;
import interfaces.IStatement;
import javafx.util.Pair;
import model.Collections.WrapperDictionary;
import model.ProgramState;
import model.procedure.Procedure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Clapa Lucian on 1/20/2016.
 */
public class CallProcedureStatement implements IStatement {

    private List<Expression> expressionList;
    private String name;

    public CallProcedureStatement(String name, List<Expression> expressionList) {
        this.expressionList = expressionList;
        this.name = name;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws DivideByZeroException, ValueNotFoundException, StatementExecutionException, InvalidPositionException, EmptyStackException {

        Map<String, Pair<List<String>, IStatement>> proceduresTable = programState.getProceduresTable();
        List<String> argumentsList = proceduresTable.get(name).getKey();
        IStatement statement = proceduresTable.get(name).getValue();

        IDictionary<String, Integer> newSymbolicTable = new WrapperDictionary<>();

        //create new symbol table
        int i = 0;
        for(String name : argumentsList){
            newSymbolicTable.put(name, expressionList.get(i).eval(programState.getMyDictionary(), programState.getHeap()));
            i++;
        }

        programState.getStackDictionary().push(newSymbolicTable);
        programState.getExecutionStack().push(programState.getProceduresTable().get(name).getValue());

        programState.getExecutionStack().push(new ReturnStatement());
        return null;
    }

    @Override
    public String toString() {
        return "Call " + "(" + name +  expressionList.toString() + ")";
    }
}
