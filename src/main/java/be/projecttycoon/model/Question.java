package be.projecttycoon.model;

import be.projecttycoon.model.ScoreEngine.ScoreFormat;

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
    private ScoreFormat format;

    @OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public Question(){
        answers =  new ArrayList<>();
    }

    public Question(String question, ScoreFormat format, List<Answer> answers) {
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

    public ScoreFormat getFormat() {
        return format;
    }

    public void setFormat(ScoreFormat format) {
        this.format = format;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers.removeAll(this.answers);
        this.answers.addAll(answers);
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", format=" + format +
                ", answers=" + answers +
                '}';
    }
}

