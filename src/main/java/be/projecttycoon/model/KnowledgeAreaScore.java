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

    public KnowledgeAreaScore() {
    }

    public KnowledgeAreaScore(KnowledgeArea knowledgeArea) {
        this.knowledgeArea = knowledgeArea;
    }

    public KnowledgeArea getKnowledgeArea() {
        return knowledgeArea;
    }

    public void setKnowledgeArea(KnowledgeArea knowledgeArea) {
        this.knowledgeArea = knowledgeArea;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
