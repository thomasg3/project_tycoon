package be.projecttycoon.model;

import javax.persistence.*;

/**
 * Created by thomas on 11/02/16.
 */
@Entity
public class KnowledgeAreaScore {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private KnowledgeArea knowledgeArea;
    private int score;
    private String answer;

    public KnowledgeAreaScore() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public KnowledgeAreaScore(KnowledgeArea knowledgeArea) {
        this.knowledgeArea = knowledgeArea;
    }

    public KnowledgeAreaScore(KnowledgeArea knowledgeArea, int score, String answer) {
        this.knowledgeArea = knowledgeArea;
        this.score = score;
        this.answer = answer;
    }

    public KnowledgeArea getKnowledgeArea() {
        return knowledgeArea;
    }

    public void setKnowledgeArea(KnowledgeArea knowledgeArea) {
        this.knowledgeArea = knowledgeArea;
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
