package model.Expresions;

import interfaces.IDictionary;

/**
 * Created by Lucian on 10/11/2015.
 */
public class VariableExpression extends Expression {

    private String mId;

    public VariableExpression(String mId) {
        this.mId = mId;
    }

    @Override
    public int eval(IDictionary<String, Integer> table) {
        return table.lookUp(mId);
    }

    @Override
    public String toString() {
        return mId;
    }
}
