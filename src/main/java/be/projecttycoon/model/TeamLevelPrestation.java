package be.projecttycoon.model;

import be.projecttycoon.model.level.Level;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public int getLevelScore(){
        if(level.teamsCanSeePoints())
            return knowledgeAreaScores.stream().map(kas -> kas.getScore()).reduce(0, (x,y) -> x + y);
        else return 0;
    }
}
