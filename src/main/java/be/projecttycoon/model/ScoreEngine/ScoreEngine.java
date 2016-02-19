package be.projecttycoon.model.ScoreEngine;

import be.projecttycoon.model.LevelKnowledgeArea;
import be.projecttycoon.model.Question;
import be.projecttycoon.model.TeamLevelPrestation;
import be.projecttycoon.model.level.Level;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 16-2-2016.
 */
@Entity
public class ScoreEngine {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Level> levels;

    public ScoreEngine(){
        this.levels = new ArrayList<>();
    }

    public ScoreEngine(List<Level> levels) {
        this.levels = levels;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public Level getLevel(long id){
        Level level = null;
        for (Level l : levels){
            if(l.getId() == id){
                level = l;
            }
        }
        return level;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public void addLevels(List<Level> levels){
        for (Level l: levels) {
            if(!this.levels.contains(l)){
                this.levels.add(l);
            }
        }
    }
    public void calculateScores(List<TeamLevelPrestation> teamLevelPrestations, List<LevelKnowledgeArea> levelKnowledgeAreas){
        for (TeamLevelPrestation tlp : teamLevelPrestations) {
            for(int i =0; i<= tlp.getKnowledgeAreaScores().size(); i++){
                String teamanwser = tlp.getKnowledgeAreaScores().get(i).getAnswer();
                Question question = levelKnowledgeAreas.get(i).getQuestion();
            }
        }
    }

    private ScoreFormat getScore(Question question){

        return null;
    }





}
