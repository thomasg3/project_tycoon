package be.projecttycoon.model.ScoreEngine;

import be.projecttycoon.model.KnowledgeAreaScore;
import be.projecttycoon.model.LevelKnowledgeArea;
import be.projecttycoon.model.Question;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.EnumeratioCalculation;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.IntCalculation;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.RangeCalculation;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.StringCalculation;
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
        CalculationStrategy calculationStrategy;

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
                    System.out.println("knowledgeare score na engine: " + knowledgeAreaScore.getScore());

                } else{
                    knowledgeAreaScore.setScore(0);
                }
            }
        }
    }




}
