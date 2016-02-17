package be.projecttycoon.model;

import javax.persistence.*;

/**
 * Created by Jeroen on 16-2-2016.
 */

@Entity
public class LevelKnowledgeArea {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    private Question question;
    @ManyToOne
    private KnowledgeArea knowledgeArea;

    public LevelKnowledgeArea(){
        this.setQuestion(new Question());
    };

    public LevelKnowledgeArea(Question question, KnowledgeArea knowledgeArea) {
        this.question = question;
        setKnowledgeArea(knowledgeArea);
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

    @Override
    public String toString() {
        return "LevelKnowledgeArea{" +
                "id=" + id +
                ", question=" + question +
                ", knowledgeArea=" + knowledgeArea +
                '}';
    }
}
