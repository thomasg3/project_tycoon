package be.projecttycoon.model.ScoreEngine.util;

/**
 * Created by Jeroen on 19-2-2016.
 */
public class Splitter {

    public static String[] split(String string) {
        String[] split;
        if (string.contains("-")) {
            split = string.split("-");
        } else if (string.contains("_")) {
            split = string.split("_");
        } else if (string.contains(";")) {
            split = string.split(";");
        } else if (string.contains(" ")) {
            split = string.split(" ");
        } else {
            split = new String[1];
            split[0] = string;
            //throw new IllegalArgumentException("String " + string + " does not contain delimiter");
        }
        return split;
    }

}
