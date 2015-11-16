package Exceptions;

/**
 * Created by Lucian on 11/10/2015.
 */
public class DivideByZeroException extends Exception{
    private static final String MESSAGE = "Divide by zero!";

    public DivideByZeroException() {
        super(MESSAGE);
    }
}

