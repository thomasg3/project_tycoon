package be.projecttycoon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Jeroen on 15-2-2016.
 */

@Entity
public class Question {

    @Id
    @GeneratedValue
    private long id;

    private String question;
    private String answer;

    public Question(){

    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public boolean isCorrect(String answer){
        boolean correct = false;
        if(this.answer.equals(answer)){
            correct = true;
        }
        return correct;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

