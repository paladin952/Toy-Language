using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Exceptions;
using ToyLanguage.Interfaces;
using ToyLanguage.Model.Expressions;
using ToyLanguage.Model.Statements;
using ToyLanguage.Utils;

namespace ToyLanguage.UiPackage
{
    class UserInterface : Controller.PrintState
    {
       
        /**
         * Controller instance
         */
        private Controller controller;

        /**
         * Constructor
         */
        public UserInterface(Controller controller)
        {
            this.controller = controller;
            this.controller.setListener(this);
        }

        /**
         * Runs the ui
         */
        public void run()
        {
            do
            {
                Console.WriteLine("--1 Compound statement");
                Console.WriteLine("--2 Run");
                Console.WriteLine("--3 Print flag");
                Console.WriteLine("\n>");

                int input = Convert.ToInt32(Console.ReadLine());
                switch (input)
                {
                    case 1: // everything starts with a compound
                        IMyStatement statement = createCompoundStatement();
                        controller.createProgram(statement);
                        break;
                    case 2: //run
                        Console.WriteLine(MenuUtils.getSteps());
                     
                        int step = Convert.ToInt32(Console.ReadLine());
                        if (step == 1)
                        { // one step at a time
                            try
                            {
                                controller.runOneStep();
                            }
                            catch (StatementExecutionException e)
                            {
                                
                            }
                        }
                        else
                        { //all steps
                            try
                            {
                                controller.runAllSteps();
                            }
                            catch (StatementExecutionException e)
                            {
                                
                            }
                        }
                        break;
                    case 3://set print flasg
                        Console.WriteLine("1 to print");
                        Console.WriteLine("2 to not print");
                        int result = Convert.ToInt32(Console.ReadLine());
                        if (result == 1)
                        {
                            Controller.PRINT_FLAG = true;
                        }
                        else
                        {
                            Controller.PRINT_FLAG = false;
                        }
                        break;
                    case 5:
                        /**a=10+(2<6)*/
                        IMyStatement myStatement = new CompoundStatement(new AssignStatement("a", new ArithmeticExpression("+", new ConstantExpression(10),
                                new BooleanExpression("<", new ConstantExpression(2), new ConstantExpression(6)))), new PrintStatement(new VariableExpression("a")));
                        controller.createProgram(myStatement);
                        /**a=1==(2!=3)*/
                        IMyStatement myStatemetn2 = new CompoundStatement(new AssignStatement("a", new BooleanExpression("==", new ConstantExpression(1),
                                new BooleanExpression("!=", new ConstantExpression(2), new ConstantExpression(3)))), new PrintStatement(new VariableExpression("a")));

                        controller.createProgram(myStatemetn2);
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
        private IExpressions getExpressionFromUser(String message)
        {
            Console.WriteLine(message);
            Console.WriteLine(MenuUtils.EXPRESSIONS);
            int input = Convert.ToInt32(Console.ReadLine());
            IExpressions expression;
            switch (input)
            {
                case 1: // Arithmetic
                    expression = createArithmeticExpression();
                    break;
                case 2: //Constant
                    expression = createConstantExpression();
                    break;
                default://Variable
                    expression = createVariableExpression();
                    break;

            }
            return expression;
        }

        /**
         * Ask user for what statement wants to create
         *
         * @param message The message to be shown to user
         * @return The IStatement created by user
         */
        private IMyStatement getStatementFromUser(String message)
        {
            IMyStatement statement;
            Console.WriteLine(message);
            Console.WriteLine(MenuUtils.getChooseStatement());
            int input1 = Convert.ToInt32(Console.ReadLine());
            switch (input1)
            {
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
                    break;
            }
            return statement;
        }

        /**
         * Create a variable expression
         * Let user choose the value
         *
         * @return The VariableExpression created by user
         */
        private IExpressions createVariableExpression()
        {
            Console.WriteLine("--Enter variable: ");
            String var = Console.ReadLine();
            return new VariableExpression(var);
        }

        /**
         * Create a constant expression
         * Let user choose the value
         *
         * @return The ConstantExpression created by user
         */
        private IExpressions createConstantExpression()
        {
            Console.WriteLine("--Enter number: ");
            int number = Convert.ToInt32(Console.ReadLine());

            return new ConstantExpression(number);
        }

        /**
         * Create an Arithmetic expression
         * Let user choose the operator and another 2 expression
         *
         * @return The ArithmeticExpression created by user
         */
        private IExpressions createArithmeticExpression()
        {
            IExpressions expression1;
            IExpressions expression2;
            String theOperator;

            Console.WriteLine("--Enter operator");
            theOperator = Console.ReadLine();

            expression1 = getExpressionFromUser("--Enter first expression: ");
            expression2 = getExpressionFromUser("--Enter second expression: ");
            return new ArithmeticExpression(theOperator, expression1, expression2);
        }

        /**
         * Create a print Statement containing an expression
         * Let user create an expression for print statement
         *
         * @return The PrintStatement created by user
         */
        private IMyStatement createPrintStatement()
        {
            IExpressions expression = getExpressionFromUser("--Enter expression: ");
            return new PrintStatement(expression);
        }

        /**
         * Create a compound Statement containing another 2 statements
         * Let user create the statements
         *
         * @return The CompoundStatement created by user
         */
        private IMyStatement createCompoundStatement()
        {
            IMyStatement statement1;
            IMyStatement statement2;

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
        private IMyStatement createIfStatement()
        {
            IExpressions expression;
            IMyStatement correctStatement;
            IMyStatement falseStatement;

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
        private IMyStatement createAssignStatement()
        {
            IExpressions expression;
            Console.WriteLine("Enter variable name: ");
            String var = Console.ReadLine();
            expression = getExpressionFromUser("Enter expression: ");
            return new AssignStatement(var, expression);

        }

        public void print(string message)
        {
            Console.WriteLine(message);
        }
    }
}
