package be.projecttycoon.model.ScoreEngineTemplate;

import be.projecttycoon.model.LevelKnowledgeArea;
import be.projecttycoon.model.Question;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jeroen on 23-2-2016.
 */
@Entity
public class LevelTemplate {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int round;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<LevelKnowledgeAreaTemplate> levelKnowledgeAreaTemplates;

    public LevelTemplate(){}

    public LevelTemplate(String name, int round, List<LevelKnowledgeAreaTemplate> levelKnowledgeAreaTemplates) {
        this.name = name;
        this.round = round;
        this.levelKnowledgeAreaTemplates = levelKnowledgeAreaTemplates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<LevelKnowledgeAreaTemplate> getLevelKnowledgeAreaTemplates() {
        return levelKnowledgeAreaTemplates;
    }

    public void setLevelKnowledgeAreaTemplates(List<LevelKnowledgeAreaTemplate> levelKnowledgeAreaTemplates) {
        this.levelKnowledgeAreaTemplates = levelKnowledgeAreaTemplates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
