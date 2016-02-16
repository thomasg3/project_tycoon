package be.projecttycoon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Jeroen on 16-2-2016.
 */
@Entity
public class Answer {

    @Id
    @GeneratedValue
    private long id;

    private String answer;
    private int score;

    public Answer(){};

    public Answer(String answer, int score) {
        this.answer = answer;
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
