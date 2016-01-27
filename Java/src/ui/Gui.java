package ui;

import Exceptions.*;
import Exceptions.EmptyStackException;
import controller.Controller;
import interfaces.Expression;
import interfaces.IProcedure;
import interfaces.IStatement;
import javafx.util.Pair;
import model.Expresions.ArithmeticExpression;
import model.Expresions.ConstantExpression;
import model.Expresions.ReadHeap;
import model.Expresions.VariableExpression;
import model.Statements.*;
import model.procedure.Procedure;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Clapa Lucian on 1/12/2016.
 */
public class Gui extends JFrame implements Controller.PrintState {

    private Controller controller;

    private JPanel mainPanel;
    private JTextArea textArea;
    private JButton oneStepButton;
    private JButton allStepsButton;
    private JButton addStatementButton;
    private JButton serializeButton;
    private JButton deSerializeButton;
    private JButton saveToFileButton;

    public Gui(Controller controller) {
        this.controller = controller;
        controller.setListener(this);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3));

        addTextArea();
        addButtons();
        addUiListeners();
        getContentPane().add(mainPanel);
    }

    /**
     * Add ui listeners
     */
    private void addUiListeners() {
        loadProgram();
        oneStepButton.addActionListener(e -> {
            try {
                controller.runOneStep();
            } catch (StatementExecutionException e1) {
                e1.printStackTrace();
            } catch (EmptyStackException e1) {
                e1.printStackTrace();
            } catch (ValueNotFoundException e1) {
                e1.printStackTrace();
            } catch (InvalidPositionException e1) {
                e1.printStackTrace();
            } catch (DivideByZeroException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        allStepsButton.addActionListener(e -> {
            loadProgram();
            try {
                controller.runAllSteps();
            } catch (StatementExecutionException | EmptyStackException | InvalidPositionException ex) {
                System.out.println("We are sorry, your program is not valid!");
            } catch (ValueNotFoundException ex) {
                System.out.println("Value not found!");
            } catch (DivideByZeroException ex) {
                System.out.println("Divide by zero!");
            } catch (InterruptedException ex) {
                System.out.println("Intreruption exception");
            }
        });

        addStatementButton.addActionListener(e -> {
            StatementDialog statementDialog = new StatementDialog(this);
            statementDialog.showDialog();
            IStatement statement = statementDialog.getCurrentStatement();
//            controller.createProgram(statement);
        });

        saveToFileButton.addActionListener(e -> {
            Controller.PRINT_FLAG = Constants.PRINT_IN_FILE;
            allStepsButton.doClick();
        });

        serializeButton.addActionListener(e -> {
            controller.serialize();
        });

        deSerializeButton.addActionListener(e -> {
            controller.deSerialize();
        });
    }

    /**
     * Load hardcoded program
     */
    private void loadProgram() {
        IStatement whileStatement =
                new CompoundStatement(new AssignStatement("a", new ConstantExpression(12)),
                        new WhileStatement(new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                new AssignStatement("a", new ArithmeticExpression("-", new VariableExpression("a"), new ConstantExpression(1)))), new VariableExpression("a")));

        IStatement fork =
                new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                        new CompoundStatement(new HeapAllocation("a", new ConstantExpression(22)),
                                new CompoundStatement(new ForkStatement(new WriteHeapStatement("a", new ConstantExpression(30))),
                                        new CompoundStatement(new AssignStatement("v", new ConstantExpression(32)),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new CompoundStatement(new PrintStatement(new ReadHeap("a")),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap("a")))))))));

        IStatement forStatement =
                new CompoundStatement(new AssignStatement("v", new ConstantExpression(0)),
                        new ForStatement(new VariableExpression("v"),
                                new ConstantExpression(5), new AssignStatement("v", new ArithmeticExpression("+", new VariableExpression("v"), new ConstantExpression(1))), new PrintStatement(new VariableExpression("v"))));


        /**procedure example*/
        java.util.List<String> sumParams = new ArrayList<>();
        sumParams.add("a");
        sumParams.add("b");
        IStatement sumStatement = new CompoundStatement(new AssignStatement("v", new ArithmeticExpression("+", new VariableExpression("a"), new VariableExpression("b"))), new PrintStatement(new VariableExpression("v")));
        IProcedure sumProcedure = new Procedure("sumProcedure", sumParams, sumStatement);


        java.util.List<String> productParams = new ArrayList<>();
        productParams.add("a");
        productParams.add("b");
        IStatement productStatemnet = new CompoundStatement(new AssignStatement("v", new ArithmeticExpression("*", new VariableExpression("a"), new VariableExpression("b"))), new PrintStatement(new VariableExpression("v")));
        IProcedure producProcedure = new Procedure("producProcedure", productParams, productStatemnet);

        Map<String, Pair<List<String>, IStatement>> proceduresTable = new HashMap<>();

        Pair<List<String>, IStatement> sumPair = new Pair<>(sumParams, sumStatement);
        Pair<List<String>, IStatement> productPair = new Pair<>(productParams, productStatemnet);
        proceduresTable.put(sumProcedure.getName(), sumPair);
//        proceduresTable.put(producProcedure.getName(), productPair);

        List<Expression> sumEexpressionList = new ArrayList<>();
        sumEexpressionList.add(new ArithmeticExpression("+", new VariableExpression("v"), new ConstantExpression(10)));
        sumEexpressionList.add(new VariableExpression("w"));

        IStatement statement = new CompoundStatement(new AssignStatement("v", new ConstantExpression(2)),
                new CompoundStatement(new AssignStatement("w", new ConstantExpression(5)),
                        new CallProcedureStatement(sumProcedure.getName(),sumEexpressionList)));

        controller.createProgram(statement, proceduresTable);
    }

    /**
     * Display the main windows
     *
     * @return Return the Gui
     * @throws ClassNotFoundException
     * @throws UnsupportedLookAndFeelException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private Gui displayMainWindow() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        pack();
        setTitle("the title");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setVisible(true);
        return this;
    }

    private void addButtons() {
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 1));

        oneStepButton = new JButton("One Step");
        allStepsButton = new JButton("All Steps");
        addStatementButton = new JButton("Add Statement");
        serializeButton = new JButton("Serialize");
        deSerializeButton = new JButton("deserialize");
        saveToFileButton = new JButton("Save to File");

        buttonsPanel.add(oneStepButton);
        buttonsPanel.add(allStepsButton);
        buttonsPanel.add(addStatementButton);
        buttonsPanel.add(serializeButton);
        buttonsPanel.add(deSerializeButton);
        buttonsPanel.add(saveToFileButton);

        mainPanel.add(buttonsPanel);
    }

    public Gui myShow() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                displayMainWindow();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return this;
    }

    /**
     * Add the text area
     */
    private void addTextArea() {
        textArea = new JTextArea("", 30, 2);
        final JScrollPane scroll = new JScrollPane(textArea);

        textArea.setEnabled(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setDisabledTextColor(Color.BLACK);
        mainPanel.add(scroll);
    }

    @Override
    public void print(String message) {
        textArea.append(message);
        System.out.print(message);
    }
}
