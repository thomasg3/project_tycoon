package be.projecttycoon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by Jeroen on 16-2-2016.
 */

@Entity
public class LevelKnowledgeArea {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Question question;
    @OneToOne
    private KnowledgeArea knowledgeArea;

    public LevelKnowledgeArea(){};

    public LevelKnowledgeArea(Question question, KnowledgeArea knowledgeArea) {
        this.question = question;
        this.knowledgeArea = knowledgeArea;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public KnowledgeArea getKnowledgeArea() {
        return knowledgeArea;
    }

    public void setKnowledgeArea(KnowledgeArea knowledgeArea) {
        this.knowledgeArea = knowledgeArea;
    }
}
