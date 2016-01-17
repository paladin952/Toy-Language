package ui;

import interfaces.Expression;
import interfaces.IStatement;
import model.Expresions.ConstantExpression;
import model.Statements.*;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Clapa Lucian on 1/17/2016.
 */
public class StatementDialog extends JDialog {

    private JRadioButton compoundRadioButton;
    private JRadioButton assignRadioButton;
    private JRadioButton forkRadioButton;
    private JRadioButton heapAllocationRadioButton;
    private JRadioButton ifRadioButton;
    private JRadioButton printRadioButton;
    private JRadioButton skipRadioButton;
    private JRadioButton switchRadioButton;
    private JRadioButton whileRadioButton;
    private JRadioButton writeHeapsRadioButton;
    private JPanel radialButtonsPanel;
    private JFrame frame;
    private java.util.List<JRadioButton> buttonsList;
    private IStatement currentStatement;

    public StatementDialog(JFrame frame) {
        super(frame, true);
        this.frame = frame;
        setupDialog();
        addRadialButtons();
    }

    private void addRadialButtons() {
        setRadioButtons();
        addButtonToList();
        addListenersToButtons();
        radialButtonsPanel = new JPanel();
        radialButtonsPanel.setSize(new Dimension(700, 700));
        radialButtonsPanel.setLayout(new GridLayout(5, 2));

        for (JRadioButton button : buttonsList) {
            radialButtonsPanel.add(button);
        }

        add(radialButtonsPanel);
    }

    private void addListenersToButtons() {

        compoundRadioButton.addActionListener(e -> {
            selectButton(compoundRadioButton);
            StatementDialog statementDialog1 =  new StatementDialog(frame);
            statementDialog1.showDialog();

            IStatement statement1 = statementDialog1.getCurrentStatement();

            StatementDialog statementDialog2 = new StatementDialog(frame);
            statementDialog2.showDialog();

            IStatement statement2 = statementDialog2.getCurrentStatement();

            currentStatement = new CompoundStatement(statement1, statement2);
            setVisible(false);
        });

        printRadioButton.addActionListener(e -> {
            ExpressionsDialog expressionsDialog = new ExpressionsDialog(frame);
            expressionsDialog.setVisible(true);
            Expression expression = expressionsDialog.getCurrentExpression();
            currentStatement = new PrintStatement(expression);
            setVisible(false);
        });

        assignRadioButton.addActionListener(e -> {
            String parameter = JOptionPane.showInputDialog("Enter variable:");

            ExpressionsDialog expressionsDialog = new ExpressionsDialog(frame);
            expressionsDialog.setVisible(true);
            Expression expression = expressionsDialog.getCurrentExpression();

            currentStatement = new AssignStatement(parameter, expression);
            setVisible(false);
        });

        forkRadioButton.addActionListener(e -> {
            StatementDialog statementDialog1 =  new StatementDialog(frame);
            statementDialog1.showDialog();

            IStatement statement1 = statementDialog1.getCurrentStatement();
            currentStatement = new ForkStatement(statement1);
            setVisible(false);
        });

        heapAllocationRadioButton.addActionListener(e -> {
            String parameter = JOptionPane.showInputDialog("Enter variable:");

            ExpressionsDialog expressionsDialog = new ExpressionsDialog(frame);
            expressionsDialog.setVisible(true);
            Expression expression = expressionsDialog.getCurrentExpression();

            currentStatement = new HeapAllocation(parameter, expression);
            setVisible(false);
        });

        ifRadioButton.addActionListener(e -> {
            ExpressionsDialog expressionsDialog = new ExpressionsDialog(frame);
            expressionsDialog.setVisible(true);
            Expression expression = expressionsDialog.getCurrentExpression();

            StatementDialog statementDialog1 =  new StatementDialog(frame);
            statementDialog1.showDialog();
            IStatement statement1 = statementDialog1.getCurrentStatement();

            StatementDialog statementDialog2 = new StatementDialog(frame);
            statementDialog2.showDialog();
            IStatement statement2 = statementDialog2.getCurrentStatement();

            currentStatement = new IfStatement(expression, statement1, statement2);
            setVisible(false);
        });

        skipRadioButton.addActionListener(e -> currentStatement = new SkipStatement());

        switchRadioButton.addActionListener(e -> {
            ExpressionsDialog expressionsDialog = new ExpressionsDialog(frame);
            expressionsDialog.setVisible(true);
            Expression expression = expressionsDialog.getCurrentExpression();

            ExpressionsDialog expressionsDialog1 = new ExpressionsDialog(frame);
            expressionsDialog1.setVisible(true);
            Expression expression1 = expressionsDialog1.getCurrentExpression();

            ExpressionsDialog expressionsDialog2 = new ExpressionsDialog(frame);
            expressionsDialog2.setVisible(true);
            Expression expression2 = expressionsDialog2.getCurrentExpression();

            StatementDialog statementDialog =  new StatementDialog(frame);
            statementDialog.showDialog();
            IStatement statement = statementDialog.getCurrentStatement();

            StatementDialog statementDialog1 =  new StatementDialog(frame);
            statementDialog1.showDialog();
            IStatement statement1 = statementDialog1.getCurrentStatement();

            StatementDialog statementDialog2 = new StatementDialog(frame);
            statementDialog2.showDialog();
            IStatement statement2 = statementDialog2.getCurrentStatement();

            currentStatement = new SwitchStatement(expression, expression1, expression2, statement, statement1, statement2);
            setVisible(false);
        });

        writeHeapsRadioButton.addActionListener(e -> {
            StatementDialog statementDialog =  new StatementDialog(frame);
            statementDialog.showDialog();
            IStatement statement = statementDialog.getCurrentStatement();

            ExpressionsDialog expressionsDialog2 = new ExpressionsDialog(frame);
            expressionsDialog2.setVisible(true);
            Expression expression2 = expressionsDialog2.getCurrentExpression();

            currentStatement = new WhileStatement(statement, expression2);
            setVisible(false);
        });

    }

    public void showDialog(){
        setVisible(true);
    }

    public IStatement getCurrentStatement(){
        return currentStatement;
    }

    private void addButtonToList() {
        buttonsList = new ArrayList<>();

        buttonsList.add(compoundRadioButton);
        buttonsList.add(assignRadioButton);
        buttonsList.add(forkRadioButton);
        buttonsList.add(heapAllocationRadioButton);
        buttonsList.add(ifRadioButton);
        buttonsList.add(printRadioButton);
        buttonsList.add(skipRadioButton);
        buttonsList.add(switchRadioButton);
        buttonsList.add(whileRadioButton);
        buttonsList.add(writeHeapsRadioButton);
    }

    private void selectButton(JRadioButton button) {
        for (JRadioButton currentButton : buttonsList) {
            if (currentButton != button) {
                currentButton.setSelected(false);
            } else {
                currentButton.setSelected(true);
            }
        }
    }

    private void setRadioButtons() {
        compoundRadioButton = new JRadioButton("Compound");
        assignRadioButton = new JRadioButton("Assign");
        forkRadioButton = new JRadioButton("Fork");
        heapAllocationRadioButton = new JRadioButton("Heap Allocation");
        ifRadioButton = new JRadioButton("IF");
        printRadioButton = new JRadioButton("Print");
        skipRadioButton = new JRadioButton("Skip");
        switchRadioButton = new JRadioButton("Switch");
        whileRadioButton = new JRadioButton("While");
        writeHeapsRadioButton = new JRadioButton("Write Heap");
    }

    private void setupDialog() {
        setTitle("Statements");
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(this);
        setModal(true);
    }
}
