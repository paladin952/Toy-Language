package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Clapa Lucian on 1/17/2016.
 */
public class StandardInputDialog extends JDialog {

    private JFrame frame;
    private JTextField textField;
    public StandardInputDialog(JFrame owner) {
        super(owner, true);
        this.frame = owner;

        textField = new JTextField("Enter: ", 1);
        add(textField);
    }

    public String getExpressionParameter(){
        return textField.getText();
    }
}
