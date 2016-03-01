package be.projecttycoon.rest.util;

import be.projecttycoon.model.KnowledgeAreaScore;
import be.projecttycoon.model.TeamLevelPrestation;
import be.projecttycoon.model.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 01/03/16.
 */
public class TeamLevelPrestationBean {
    private long id;
    private LevelBean level;
    private List<KnowledgeAreaScore> knowledgeAreaScores;

    public TeamLevelPrestationBean(){
        knowledgeAreaScores = new ArrayList<>();
    }

    public TeamLevelPrestationBean (TeamLevelPrestation p){
        this(p.getId(),new LevelBean(p.getLevel().getId(),p.getLevel().getName(),p.getLevel().getState()),p.getKnowledgeAreaScores());
    }

    public TeamLevelPrestationBean(long id, LevelBean level, List<KnowledgeAreaScore> knowledgeAreaScores) {
        this.id = id;
        this.level = level;
        this.knowledgeAreaScores = knowledgeAreaScores;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LevelBean getLevel() {
        return level;
    }

    public void setLevel(LevelBean level) {
        this.level = level;
    }

    public List<KnowledgeAreaScore> getKnowledgeAreaScores() {
        return knowledgeAreaScores;
    }

    public void setKnowledgeAreaScores(List<KnowledgeAreaScore> knowledgeAreaScores) {
        this.knowledgeAreaScores = knowledgeAreaScores;
    }
}
