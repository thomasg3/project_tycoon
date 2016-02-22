package be.projecttycoon.model.ScoreEngine;

import be.projecttycoon.model.*;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.EnumeratioCalculation;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.IntCalculation;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.RangeCalculation;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.StringCalculation;
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
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Level> levels;

    public ScoreEngine(){
        this.levels = new ArrayList<>();
    }

    public ScoreEngine(List<Level> levels) {
        this.levels = levels;
    }

    public ScoreEngine(String name, int levels, List<KnowledgeArea> knowledgeAreas) {
        this.name = name;
        this.levels = new ArrayList<>();
        generateLevels(levels, knowledgeAreas);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private void generateLevels(int levels, List<KnowledgeArea> knowledgeAreas){
        for(int i = 1; i<=levels; i++){
            List<LevelKnowledgeArea> levelKnowledgeAreas = new ArrayList<>();
            for (KnowledgeArea k:knowledgeAreas){
                LevelKnowledgeArea lk = new LevelKnowledgeArea();
                lk.setKnowledgeArea(k);
                levelKnowledgeAreas.add(lk);
            }
            getLevels().add(new Level("Level "+ i, i, levelKnowledgeAreas));
        }
    }

    public void calculateScores(List<TeamLevelPrestation> teamLevelPrestations, List<LevelKnowledgeArea> levelKnowledgeAreas){
        CalculationStrategy calculationStrategy;
        resetScores(teamLevelPrestations);

        for (TeamLevelPrestation tlp : teamLevelPrestations) {
            for(int i =0; i<tlp.getKnowledgeAreaScores().size(); i++){
                KnowledgeAreaScore knowledgeAreaScore = tlp.getKnowledgeAreaScores().get(i);
                Question question = levelKnowledgeAreas.get(i).getQuestion();
                if(knowledgeAreaScore.getAnswer() != null && !knowledgeAreaScore.getAnswer().isEmpty()){
                    if(question.getFormat().equals(ScoreFormat.RANGE) || question.getFormat().equals(ScoreFormat.AMOUNT_RANGE) || question.getFormat().equals(ScoreFormat.PERCENT_RANGE)){
                        calculationStrategy = new RangeCalculation();
                    }
                    else if(question.getFormat().equals(ScoreFormat.ENUMERATION)){
                        calculationStrategy = new EnumeratioCalculation();
                    }
                    else if(question.getFormat().equals(ScoreFormat.INT)){
                        calculationStrategy = new IntCalculation();
                    }
                    else{
                        calculationStrategy = new StringCalculation();
                    }

                    calculationStrategy.calculateScore(knowledgeAreaScore, question);

                } else{
                    knowledgeAreaScore.setScore(0);
                }
            }
        }
    }

    private void resetScores(List<TeamLevelPrestation> teamLevelPrestations){
        for(TeamLevelPrestation tlp: teamLevelPrestations){
            for(KnowledgeAreaScore kas: tlp.getKnowledgeAreaScores()){
                kas.setScore(0);
            }
        }
    }

    @Override
    public String toString() {
        return "ScoreEngine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", levels=" + levels +
                '}';
    }
}
