package be.projecttycoon.model.ScoreEngine.util;

import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jeroen on 19-2-2016.
 */
public class Enumeration {

    private List<String> enumeration;
    private int score;

    public Enumeration(int score, List<String> enumeration) {
        this.score = score;
        this.enumeration = enumeration;
    }

    public Enumeration(int score, String[] array) {
        this.score = score;
        this.enumeration = new ArrayList<>(Arrays.asList(array));
    }

    public boolean match(String string){
        boolean match = true;
        String[] split = Splitter.split(string);
        if(split.length == enumeration.size()){
            for(int i = 0; i <= split.length -1; i++ ){
                if(!enumeration.get(i).equals("*")){
                    if(!split[i].equals(enumeration.get(i))){
                        match = false;
                        break;
                    }
                }
            }
        } else{
            match = false;
        }
        return match;
    }

    public List<String> getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(List<String> enumeration) {
        this.enumeration = enumeration;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
