package be.projecttycoon.model.ScoreEngine.util;

/**
 * Created by Jeroen on 19-2-2016.
 */
public class Between {
    private int from;
    private int to;
    private int score;

    public Between(int from, int to, int score) {
        this.from = from;
        this.to = to;
        this.score = score;
    }

    public boolean isBetween(int value){
        boolean between = false;
        if(value >= from && value <= to){
            between = true;
        }
        return between;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
