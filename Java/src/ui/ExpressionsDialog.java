package ui;

import interfaces.Expression;
import model.Expresions.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ExpressionsDialog extends JDialog {

    private JRadioButton arithmeitcRadioButton;
    private JRadioButton booleanRadioButton;
    private JRadioButton constantRadioButton;
    private JRadioButton logicRadioButton;
    private JRadioButton readHeapRadioButton;
    private JRadioButton variableRadioButton;
    private JPanel radialButtonsPanel;
    private JFrame frame;
    private java.util.List<JRadioButton> buttonsList;
    private Expression currentExpression;
    public ExpressionsDialog(JFrame frame){
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

        for(JRadioButton button : buttonsList){
            radialButtonsPanel.add(button);
        }

        add(radialButtonsPanel);
    }

    public Expression getCurrentExpression() {
        return currentExpression;
    }

    private void addListenersToButtons() {

        constantRadioButton.addActionListener(e1 -> {
            String parameter = JOptionPane.showInputDialog("Enter Input:");
            currentExpression = new ConstantExpression(Integer.parseInt(parameter));
            setVisible(false);
        });

        variableRadioButton.addActionListener(e1 -> {
            String res = JOptionPane.showInputDialog("Enter Input:");
            currentExpression = new VariableExpression(res);
            setVisible(false);
        });

        arithmeitcRadioButton.addActionListener(e1 -> {
            String operator = JOptionPane.showInputDialog("Enter operator:");

            ExpressionsDialog expressionsDialog1 = new ExpressionsDialog(frame);
            Expression expression1 = expressionsDialog1.getCurrentExpression();

            ExpressionsDialog expressionsDialog2 = new ExpressionsDialog(frame);
            Expression expression2 = expressionsDialog2.getCurrentExpression();

             currentExpression = new ArithmeticExpression(operator, expression1, expression2);
            setVisible(false);
        });

        booleanRadioButton.addActionListener(e1 -> {
            String operator = JOptionPane.showInputDialog("Enter operator:");

            ExpressionsDialog expressionsDialog1 = new ExpressionsDialog(frame);
            Expression expression1 = expressionsDialog1.getCurrentExpression();

            ExpressionsDialog expressionsDialog2 = new ExpressionsDialog(frame);
            Expression expression2 = expressionsDialog2.getCurrentExpression();

            currentExpression = new ArithmeticExpression(operator, expression1, expression2);
            setVisible(false);
        });

        logicRadioButton.addActionListener(e1 -> {
            String operator = JOptionPane.showInputDialog("Enter operator:");

            ExpressionsDialog expressionsDialog1 = new ExpressionsDialog(frame);
            Expression expression1 = expressionsDialog1.getCurrentExpression();

            ExpressionsDialog expressionsDialog2 = new ExpressionsDialog(frame);
            Expression expression2 = expressionsDialog2.getCurrentExpression();

            currentExpression = new ArithmeticExpression(operator, expression1, expression2);
            setVisible(false);
        });

        readHeapRadioButton.addActionListener(e1 -> {
            String res = JOptionPane.showInputDialog("Enter Input:");
            currentExpression = new ReadHeap(res);
            setVisible(false);
        });

    }

    private void addButtonToList(){
        buttonsList = new ArrayList<>();

        buttonsList.add(arithmeitcRadioButton);
        buttonsList.add(booleanRadioButton);
        buttonsList.add(constantRadioButton);
        buttonsList.add(logicRadioButton);
        buttonsList.add(readHeapRadioButton);
        buttonsList.add(variableRadioButton);
    }

    private void selectButton(JRadioButton button){
        for(JRadioButton currentButton : buttonsList){
            if(currentButton != button){
                currentButton.setSelected(false);
            }else{
                currentButton.setSelected(true);
            }
        }
    }

    private void setRadioButtons() {
        arithmeitcRadioButton = new JRadioButton("Arithmetic");
        booleanRadioButton = new JRadioButton("Boolean");
        constantRadioButton = new JRadioButton("Constant");
        logicRadioButton = new JRadioButton("Logical");
        readHeapRadioButton = new JRadioButton("Read Heap");
        variableRadioButton = new JRadioButton("variable");
    }

    private void setupDialog(){
        setTitle("Expressions");
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(this);
        setModal(true);
    }
}
