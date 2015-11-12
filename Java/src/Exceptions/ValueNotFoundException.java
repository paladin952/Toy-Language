package Exceptions;

/**
 * Created by Lucian on 11/7/2015.
 */
public class ValueNotFoundException extends MyException {

    private static final String MESSAGE = "The value was not found!";

    public ValueNotFoundException() {
        super(MESSAGE);
    }
}
