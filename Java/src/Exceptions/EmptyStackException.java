package Exceptions;

/**
 * Created by Lucian on 11/7/2015.
 */
public class EmptyStackException extends MyException {

    private static final String MESSAGE = "The stack is empty!";

    public EmptyStackException() {
        super(MESSAGE);
    }
}
