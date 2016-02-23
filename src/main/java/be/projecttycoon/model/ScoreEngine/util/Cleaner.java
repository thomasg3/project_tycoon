package be.projecttycoon.model.ScoreEngine.util;

/**
 * Created by Jeroen on 19-2-2016.
 */
public class Cleaner {


    public static String clean(String string) {
        if(string.contains("%")){
            string = string.replaceAll("%", "");
        }
        if(string.contains("$")){
           string = string.replaceAll("$", "");
        }
        if(string.contains("€")){
            string = string.replaceAll("€", "");
        }
        if(string.contains("£")){
            string = string.replaceAll("£", "");
        }

        string = string.replaceAll("\\s","");
        string = string.toLowerCase();
        return string;
    }

}
