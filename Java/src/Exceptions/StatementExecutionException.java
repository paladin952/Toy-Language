package Exceptions;

/**
 * Created by Lucian on 10/19/2015.
 */
public class StatementExecutionException extends Exception {
    public StatementExecutionException() {
        super("The are no statement to execute");
    }
}
