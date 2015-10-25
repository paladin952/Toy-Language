package ui;

/**
 * Created by Lucian on 10/13/2015.
 */
public class MenuUtils {

    public static String getPrint() {
        return "-- 3 for print flag";
    }

    public static String getPrintOptions(){
        return "-- 1 for false\n-- 2 for true";
    }

    public enum Type{

        PRINT_STATEMENT("print"),

        IF_STATEMENT("if"),

        ASSIGN_STATEMENT("assign"),

        COMPOUND_STATEMENT("compound"),

        STATEMENT("statement"),

        EXPRSESION("expression"),

        Exit("exit")
        ;


        private String text;
        /**
         * @param text
         */
        private Type(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }
    }
    public static String getChooseStatement(){
        return "-- " + Type.STATEMENT + "\n-- " + Type.EXPRSESION ;
    }

    public static String getStatements(){
        return "-- " + Type.COMPOUND_STATEMENT + "\n-- "+ Type.ASSIGN_STATEMENT + "\n-- "+
                Type.IF_STATEMENT +  "\n-- "+ Type.PRINT_STATEMENT;
    }

    public static String getSteps(){
        return "-- 1 for One step\n-- 2 for All steps";
    }

}
