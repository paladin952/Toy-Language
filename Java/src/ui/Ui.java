package ui;

import Exceptions.*;
import controller.Controller;
import interfaces.IStatement;
import model.Expresions.*;
import interfaces.Expression;
import model.Statements.*;
import utils.Constants;

import java.util.Scanner;


public class Ui implements Controller.PrintState {
    /**
     * Input scanned
     */
    private Scanner scanner;

    /**
     * Controller instance
     */
    private Controller controller;

    /**
     * Constructor
     */
    public Ui(Controller controller) {
        scanner = new Scanner(System.in);
        this.controller = controller;
        controller.setListener(this);
    }

    /**
     * Runs the ui
     */
    public void run() {
        do {
            System.out.println("--1 Compound statement");
            System.out.println("--2 Run");
            System.out.println("--3 Print flag");
            System.out.println("--4 Serialize");
            System.out.println("--5 deserialize");
            System.out.print("\n>");

            int input = scanner.nextInt();
            switch (input) {
                case 1: // everything starts with a compound
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
                        } catch (StatementExecutionException | EmptyStackException e) {
                            System.out.println("We are sorry, your program is not valid!");
                        } catch (ValueNotFoundException | InvalidPositionException e) {
                            System.out.println(e.toString());
                        } catch (DivideByZeroException e) {
                            System.out.println("Divide by zero!");
                        } catch (InterruptedException e) {
                            System.out.println("Intreruption exception");
                        }
                    } else { //all steps
                        try {
                            controller.runAllSteps();
                        } catch (StatementExecutionException | EmptyStackException | InvalidPositionException e) {
                            System.out.println("We are sorry, your program is not valid!");
                        } catch (ValueNotFoundException e) {
                            e.printStackTrace();
                        } catch (DivideByZeroException e) {
                            System.out.println("Divide by zero!");
                        } catch (InterruptedException e) {
                            System.out.println("Intreruption exception");
                        }
                    }
                    break;
                case 3://set print flasg
                    System.out.println("1 to print on console");
                    System.out.println("2 to save on file");
                    System.out.println("3 to not print");
                    int result = scanner.nextInt();
                    if (result == 1) {
                        Controller.PRINT_FLAG = Constants.PRINT_CONSOLE;
                    }else if(result == 2){
                        Controller.PRINT_FLAG = Constants.PRINT_IN_FILE;
                    }else{
                        Controller.PRINT_FLAG = Constants.NO_PRINT;
                    }
                    break;
                case 4: //serialization
                    controller.serialize();
                    break;
                case 5:
                    controller.deSerialize();
                    break;
                case 6: // for testing
//                    IStatement logical1 =
//                            new CompoundStatement(new AssignStatement("a", new LogicExpression("&&", new ConstantExpression(10), new ArithmeticExpression("-", new ConstantExpression(10),
//                                new ConstantExpression(10)))), new PrintStatement(new VariableExpression("a")));
//
//                    IStatement readStatement =
//                            new CompoundStatement(new AssignStatement("a", new ArithmeticExpression("+", new ConstantExpression(1), new ReadExpression())),
//                                new PrintStatement(new VariableExpression("a")));
                    IStatement whileStatement =
                            new CompoundStatement(new AssignStatement("a", new ConstantExpression(12)),
                                new WhileStatement(new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                    new AssignStatement("a",new ArithmeticExpression("-", new VariableExpression("a"), new ConstantExpression(1)))),new VariableExpression("a")));
//
//                    IStatement switchStatement =
//                            new CompoundStatement(new AssignStatement("a", new ConstantExpression(2)),
//                                new SwitchStatement(new VariableExpression("a"), new ConstantExpression(3),
//                                    new ConstantExpression(2), new PrintStatement(new ConstantExpression(100)), new PrintStatement(new ConstantExpression(3)), new PrintStatement(new ConstantExpression(2))));
                    IStatement allocation =
                            new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                                new CompoundStatement(new HeapAllocation("v", new ConstantExpression(20)),
                                    new CompoundStatement(new HeapAllocation("a", new ConstantExpression(22)) ,new PrintStatement(new VariableExpression("v")))));

                    IStatement readHeap =
                            new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                                new CompoundStatement(new HeapAllocation("v", new ConstantExpression(20)),
                                    new CompoundStatement(new HeapAllocation("a", new ConstantExpression(22)),
                                        new CompoundStatement(new PrintStatement(new ArithmeticExpression("+", new ConstantExpression(100), new ReadHeap("v"))),
                                            new PrintStatement(new ArithmeticExpression("+", new ConstantExpression(100), new ReadHeap("a")))))));

                    IStatement writeHeap =
                            new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                                new CompoundStatement(new HeapAllocation("v", new ConstantExpression(20)),
                                        new CompoundStatement(new HeapAllocation("a", new ConstantExpression(22)),
                                                new CompoundStatement(new WriteHeapStatement("a", new ConstantExpression(30)),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new ReadHeap("a")))))));

                    IStatement fork =
                            new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                                    new CompoundStatement(new HeapAllocation("a", new ConstantExpression(22)),
                                            new CompoundStatement(new ForkStatement(new WriteHeapStatement("a", new ConstantExpression(30))),
                                                    new CompoundStatement(new AssignStatement("v", new ConstantExpression(32)),
                                                            new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                    new CompoundStatement(new PrintStatement(new ReadHeap("a")),
                                                                            new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap("a")))))))));

                    IStatement fork2 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                            new CompoundStatement(new ForkStatement(new PrintStatement(new ConstantExpression(99))), new PrintStatement(new ConstantExpression(1))));

                    controller.createProgram(fork);
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
            case 3://Variable
                expression = createVariableExpression();
                break;
            default:
                expression = createBooleanExpression();

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

    private Expression createBooleanExpression(){
        Expression expression1;
        Expression expression2;
        String operator;

        System.out.println("--Enter boolean operator");
        operator = scanner.next();

        expression1 = getExpressionFromUser("--Enter first expression: ");
        expression2 = getExpressionFromUser("--Enter second expression: ");
        return new BooleanExpression(operator, expression1, expression2);
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

    @Override
    synchronized public void print(String message) {
        System.out.println(message);
    }
}
