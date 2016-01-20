package interfaces;

import javafx.util.Pair;

import java.util.List;

/**
 * Created by Clapa Lucian on 1/20/2016.
 */
public interface IProcedure {

    String getName();

    List<String> getArguments();

    IStatement getBody();

}
