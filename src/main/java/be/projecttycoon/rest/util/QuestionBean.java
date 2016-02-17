package be.projecttycoon.rest.util;

import be.projecttycoon.model.Answer;

import java.util.List;

/**
 * Created by Jeroen on 17-2-2016.
 */
public class QuestionBean {
    private String question;
    private String format;
    private List<Answer> answers;


    QuestionBean(){

    }

    public QuestionBean(List<Answer> answers, String question, String format) {
        this.answers = answers;
        this.question = question;
        this.format = format;
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
