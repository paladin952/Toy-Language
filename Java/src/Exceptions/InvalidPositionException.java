package Exceptions;

/**
 * Created by Lucian on 11/7/2015.
 */
public class InvalidPositionException extends MyException {

    private static final String MESSAGE = "Invalid position!";

    public InvalidPositionException() {
        super(MESSAGE);
    }
}
