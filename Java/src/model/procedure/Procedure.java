package model.procedure;

import interfaces.IProcedure;
import interfaces.IStatement;

import java.util.List;

/**
 * Created by Clapa Lucian on 1/20/2016.
 */
public class Procedure implements IProcedure {

    private String name;

    private List<String> argumentsList;

    private IStatement body;

    public Procedure(String name, List<String> argumentsList, IStatement body) {
        this.name = name;
        this.argumentsList = argumentsList;
        this.body = body;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getArguments() {
        return argumentsList;
    }

    @Override
    public IStatement getBody() {
        return body;
    }
}
