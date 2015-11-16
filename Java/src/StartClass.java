import controller.Controller;
import interfaces.IRepository;
import repository.Repository;
import tests.Tests;
import ui.Ui;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;
import java.util.zip.CRC32;

/**
 * Created by Lucian on 10/11/2015.
 */
public class StartClass {

    public static void main(String[] args) {
        //run tests
        new Tests();

        //init objects
        IRepository repository = new Repository();
        Controller controller = new Controller(repository);
        Ui ui = new Ui(controller);

        //start ui
        ui.run();
    }
}

