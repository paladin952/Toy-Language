package model.Expresions;

import Exceptions.ValueNotFoundException;
import interfaces.Expression;
import interfaces.IDictionary;
import interfaces.IStatement;

import java.util.Scanner;

/**
 * Created by Lucian on 11/9/2015.
 */
public class ReadExpression implements Expression {

    /**
     * The number
     */
    private int number;


    /**
     * The constructor
     *
     */
    public ReadExpression() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nENTER YOUR NUMBER: ");
        number = scanner.nextInt();
    }


    @Override
    public int eval(IDictionary<String, Integer> table) throws ValueNotFoundException {
        return number;
    }

    /**
     * String representation
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Read(" + String.valueOf(number) + ")";

    }
}
