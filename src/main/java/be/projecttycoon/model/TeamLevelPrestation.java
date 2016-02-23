package be.projecttycoon.model;

import be.projecttycoon.model.level.Level;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by thomas on 11/02/16.
 */
@Entity
public class TeamLevelPrestation {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Level level;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KnowledgeAreaScore> knowledgeAreaScores;

    public TeamLevelPrestation() {
        knowledgeAreaScores = new ArrayList<>();
    }

    public TeamLevelPrestation(Level level) {
        this();
        this.level = level;
        for(LevelKnowledgeArea lk : level.getLevelKnowledgeAreas()){
            knowledgeAreaScores.add(new KnowledgeAreaScore(lk.getKnowledgeArea()));
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<KnowledgeAreaScore> getKnowledgeAreaScores() {
        return knowledgeAreaScores;
    }

    public void setKnowledgeAreaScores(List<KnowledgeAreaScore> knowledgeAreaScores) {
        this.knowledgeAreaScores = knowledgeAreaScores;
    }

    @JsonIgnore
    public List<KnowledgeAreaScore> getPublicKnowledgeAreaScores(){
        return knowledgeAreaScores.stream()
                .map(kas -> {
                    kas.setScore(0);
                    return kas;
                })
                .collect(Collectors.toList());
    }

    public int getLevelScore(){
        return knowledgeAreaScores.stream().map(kas -> kas.getScore()).reduce(0, (x,y) -> x + y);
    }


}
