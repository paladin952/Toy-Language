package tests;

import interfaces.IStatement;
import model.Expresions.ArithmeticExpression;
import model.Expresions.ConstantExpression;
import model.Expresions.VariableExpression;
import model.Statements.AssignStatement;
import model.Statements.CompoundStatement;
import model.Statements.IfStatement;
import model.Statements.PrintStatement;

/**
 * Created by Lucian on 10/19/2015.
 */
public class StatementsTests {

    public StatementsTests() {
        testAssignStatement();
        testIfStatement();
        testCompoundStatement();
    }

    private void testCompoundStatement() {
        IStatement state3 = new CompoundStatement(new AssignStatement("a", new ArithmeticExpression("-",new ConstantExpression(2), new
                ConstantExpression(2))), new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ConstantExpression(2)), new
                AssignStatement("v", new ConstantExpression(3))), new PrintStatement(new VariableExpression("v"))));
        System.out.println(state3.toString());
    }

    private void testIfStatement() {
        IStatement state1 = new IfStatement(new VariableExpression("a"),new AssignStatement("v", new ConstantExpression(2)), new AssignStatement("v", new ConstantExpression(3)));
        System.out.println(state1.toString());
    }

    private void testAssignStatement(){
        IStatement state1 = new AssignStatement("a", new ConstantExpression(12));
        System.out.println(state1.toString());
        assert state1.toString().equals("a=12");

        AssignStatement state2 = new AssignStatement("b", new ArithmeticExpression("+", new ConstantExpression(1), new ConstantExpression(2)));
        System.out.println(state2.toString());
        assert state2.toString().equals("b=1+2");
    }
}
