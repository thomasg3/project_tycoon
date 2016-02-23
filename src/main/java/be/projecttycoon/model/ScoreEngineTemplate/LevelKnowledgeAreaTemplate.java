package be.projecttycoon.model.ScoreEngineTemplate;

import be.projecttycoon.model.KnowledgeArea;
import be.projecttycoon.model.Question;

import javax.persistence.*;

/**
 * Created by Jeroen on 23-2-2016.
 */
@Entity
public class LevelKnowledgeAreaTemplate {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Question question;
    @ManyToOne
    private KnowledgeArea knowledgeArea;

    public LevelKnowledgeAreaTemplate(KnowledgeArea knowledgeArea, Question question) {
        this.knowledgeArea = knowledgeArea;
        this.question = question;
    }

    public LevelKnowledgeAreaTemplate() {
        this.setQuestion(new Question());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
