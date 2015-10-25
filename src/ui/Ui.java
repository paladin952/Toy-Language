package ui;

import Exceptions.StatementExecutionException;
import controller.Controller;

import java.util.Scanner;

/**
 * Created by Lucian on 10/11/2015.
 */
public class Ui {
    /**
     * Input scanned
     */
    private Scanner scanner;

    /**
     * String input
     */
    private int input;

    /**
     * Controller instance
     */
    private Controller controller;

    /**
     * Consstructor
     */
    public Ui(Controller controller) {
        scanner = new Scanner(System.in);
        this.controller = controller;
    }

    /**
     * Runs the ui
     */
    public void run() {
        System.out.print(MenuUtils.getChooseStatement());
        do {
            System.out.println(MenuUtils.getSteps());
            System.out.println(MenuUtils.getPrint());
            System.out.print(">");
            input = scanner.nextInt();
            switch (input){
                case 1: //one step
                    try {
                        controller.runOneStep();
                    } catch (StatementExecutionException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2: //all steps
                    try {
                        controller.runAllSteps();
                    } catch (StatementExecutionException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3: //print
                    System.out.println(MenuUtils.getPrintOptions());
                    int input = scanner.nextInt();
                    if(input == 1){
                        Controller.shouldPrint = false;
                    }else{
                        Controller.shouldPrint = true;
                    }
                    break;
            }
        } while (true);
    }


    /**
     * Return the type of menu shoud be shown when user types
     *
     * @param input String input from user
     * @return The Menu.Type of the input
     */
    private MenuUtils.Type parseUserInput(String input) {
        if (input.equals(MenuUtils.Type.STATEMENT.toString())) {
            return MenuUtils.Type.STATEMENT;
        } else if (input.equals(MenuUtils.Type.EXPRSESION)) {
            return MenuUtils.Type.EXPRSESION;
        } else {
            return MenuUtils.Type.Exit;
        }
    }
}
