package Exceptions;

/**
 * Created by Lucian on 11/7/2015.
 */
public class EmptyListException extends MyException {

    private static final String MESSAGE = "The list is empty!";

    public EmptyListException() {
        super(MESSAGE);
    }
}
