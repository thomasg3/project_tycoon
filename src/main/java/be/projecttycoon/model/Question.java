package be.projecttycoon.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 15-2-2016.
 */

@Entity
public class Question {

    @Id
    @GeneratedValue
    private long id;

    private String question;
    private String format;

    @OneToMany(cascade= CascadeType.ALL)
    private List<Answer> answers;

    public Question(){
        this.answers = new ArrayList<>();
    };

    public Question(String question, String format, List<Answer> answers) {
        this.question = question;
        this.format = format;
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}

