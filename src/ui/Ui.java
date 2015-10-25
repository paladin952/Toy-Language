package ui;

import Exceptions.StatementExecutionException;
import controller.Controller;
import interfaces.IStatement;
import model.Expresions.ArithmeticExpression;
import model.Expresions.ConstantExpression;
import model.Expresions.Expression;
import model.Expresions.VariableExpression;
import model.Statements.AssignStatement;
import model.Statements.CompoundStatement;
import model.Statements.IfStatement;
import model.Statements.PrintStatement;

import java.util.Scanner;

/**
 * Created by Lucian on 10/11/2015.
 */
public class Ui {
    /**
     * Input scanned
     */
    private Scanner scanner;

    /**
     * String input
     */
    private int input;

    /**
     * Controller instance
     */
    private Controller controller;

    /**
     * Consstructor
     */
    public Ui(Controller controller) {
        scanner = new Scanner(System.in);
        this.controller = controller;
    }

    /**
     * Runs the ui
     */
    public void run() {
        do {
            System.out.println("--1 Compound statement");
            System.out.println("--2 Run");
            System.out.println("--3 Print flag");
            System.out.print("\n>");
            input = scanner.nextInt();
            switch (input) {
                case 1: // everytinhg starts with a compound
                    IStatement statement = createCompoundStatement();
                    controller.createProgram(statement);
                    break;
                case 2: //run
                    System.out.println(MenuUtils.getSteps());
                    System.out.print("\n>");
                    int step = scanner.nextInt();
                    if (step == 1) { // one step at a time
                        try {
                            controller.runOneStep();
                        } catch (StatementExecutionException e) {
                            e.printStackTrace();
                        }
                    } else { //all steps
                        try {
                            controller.runAllSteps();
                        } catch (StatementExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3://set print flasg
                    System.out.println("1 to print");
                    System.out.println("2 to not print");
                    int result = scanner.nextInt();
                    if (result == 1) {
                        Controller.PRINT_FLAG = true;
                    }
                    break;

            }
        } while (true);
    }

    /**
     * Ask user for what expression wants to create
     *
     * @param message The message to be shown to user
     * @return The Expression created by user
     */
    private Expression getExpressionFromUser(String message) {
        System.out.println(message);
        System.out.println(MenuUtils.EXPRESSIONS);
        int input = scanner.nextInt();
        Expression expression;
        switch (input) {
            case 1: // Arithmetic
                expression = createArithmeticExpression();
                break;
            case 2: //Constant
                expression = createConstantExpression();
                break;
            default://Variable
                expression = createVariableExpression();

        }
        return expression;
    }

    /**
     * Ask user for what statement wants to create
     *
     * @param message The message to be shown to user
     * @return The IStatement created by user
     */
    private IStatement getStatementFromUser(String message) {
        IStatement statement;
        System.out.println(message);
        System.out.println(MenuUtils.getChooseStatement());
        int input1 = scanner.nextInt();
        switch (input1) {
            case 1:
                statement = createCompoundStatement();
                break;
            case 2:
                statement = createAssignStatement();
                break;
            case 3:
                statement = createIfStatement();
                break;
            default:
                statement = createPrintStatement();
        }
        return statement;
    }

    /**
     * Create a variable expression
     * Let user choose the value
     *
     * @return The VariableExpression created by user
     */
    private Expression createVariableExpression() {
        Expression expression;
        System.out.println("--Enter variable: ");
        String var = scanner.next();
        return new VariableExpression(var);
    }

    /**
     * Create a constant expression
     * Let user choose the value
     *
     * @return The ConstantExpression created by user
     */
    private Expression createConstantExpression() {
        Expression expression;
        System.out.println("--Enter number: ");
        int number = scanner.nextInt();

        return new ConstantExpression(number);
    }

    /**
     * Create an Arithmetic expression
     * Let user choose the operator and another 2 expression
     *
     * @return The ArithmeticExpression created by user
     */
    private Expression createArithmeticExpression() {
        Expression expression1;
        Expression expression2;
        String operator;

        System.out.println("--Enter operator");
        operator = scanner.next();

        expression1 = getExpressionFromUser("--Enter first expression: ");
        expression2 = getExpressionFromUser("--Enter second expression: ");
        return new ArithmeticExpression(operator, expression1, expression2);
    }

    /**
     * Create a print Statement containing an expression
     * Let user create an expression for print statement
     *
     * @return The PrintStatement created by user
     */
    private IStatement createPrintStatement() {
        Expression expression = getExpressionFromUser("--Enter expression: ");
        return new PrintStatement(expression);
    }

    /**
     * Create a compound Statement containing another 2 statements
     * Let user create the statements
     *
     * @return The CompoundStatement created by user
     */
    private IStatement createCompoundStatement() {
        IStatement statement1;
        IStatement statement2;

        statement1 = getStatementFromUser("--Enter first statement: ");
        statement2 = getStatementFromUser("--Enter second statement: ");
        return new CompoundStatement(statement1, statement2);
    }

    /**
     * Create an If Statement containing an expression and another 2 statements
     * Let user create the expression and the statements
     *
     * @return The IfStatement created by user
     */
    private IStatement createIfStatement() {
        Expression expression;
        IStatement correctStatement;
        IStatement falseStatement;

        expression = getExpressionFromUser("Enter expression: ");
        correctStatement = getStatementFromUser("--Enter correct statement: ");
        falseStatement = getStatementFromUser("--Enter false statement: ");

        return new IfStatement(expression, correctStatement, falseStatement);
    }

    /**
     * Create an Assign Statement containing a variable name and expression to be
     * assigned to variable
     * Let user choose the name and create the expression
     *
     * @return The AssignStatement created by user
     */
    private IStatement createAssignStatement() {
        Expression expression;
        System.out.println("Enter variable name: ");
        String var = scanner.next();
        expression = getExpressionFromUser("Enter expression: ");
        return new AssignStatement(var, expression);

    }

}
