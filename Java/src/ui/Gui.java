package ui;

import Exceptions.*;
import controller.Controller;
import interfaces.IStatement;
import model.Expresions.ArithmeticExpression;
import model.Expresions.ConstantExpression;
import model.Expresions.VariableExpression;
import model.Statements.AssignStatement;
import model.Statements.CompoundStatement;
import model.Statements.PrintStatement;
import model.Statements.WhileStatement;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Clapa Lucian on 1/12/2016.
 */
public class Gui extends JFrame {

    private Controller controller;

    private JPanel mainPanel;
    private JTextArea textArea;
    private JButton oneStepButton;
    private JButton allStepsButton;

    public Gui(Controller controller){
        this.controller = controller;
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
        oneStepButton.addActionListener(e -> {

        });

        allStepsButton.addActionListener(e -> {
            loadProgram();

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
        });
    }

    /**
     * Load hardcoded program
     */
    private void loadProgram(){
        IStatement whileStatement =
                new CompoundStatement(new AssignStatement("a", new ConstantExpression(12)),
                        new WhileStatement(new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                new AssignStatement("a",new ArithmeticExpression("-", new VariableExpression("a"), new ConstantExpression(1)))),new VariableExpression("a")));
        controller.createProgram(whileStatement);
    }

    private Gui displayMainWindow() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        pack();
        setTitle("the title");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setVisible(true);
        return this;
    }

    private void addButtons(){
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 1));

        oneStepButton = new JButton("One Step");
        allStepsButton = new JButton("All Steps");

        buttonsPanel.add(oneStepButton);
        buttonsPanel.add(allStepsButton);

        mainPanel.add(buttonsPanel);
    }

    public Gui myShow(){
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
    private void addTextArea(){
        textArea = new JTextArea("Welcome", 30, 1);
        textArea.setEnabled(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        mainPanel.add(textArea);
    }

}
