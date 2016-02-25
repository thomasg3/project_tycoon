package be.projecttycoon.model.ScoreEngine;

import be.projecttycoon.model.*;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.EnumeratioCalculation;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.IntCalculation;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.RangeCalculation;
import be.projecttycoon.model.ScoreEngine.CalculationStrategies.StringCalculation;
import be.projecttycoon.model.ScoreEngineTemplate.LevelKnowledgeAreaTemplate;
import be.projecttycoon.model.ScoreEngineTemplate.LevelTemplate;
import be.projecttycoon.model.ScoreEngineTemplate.ScoreEngineTemplate;
import be.projecttycoon.model.level.Level;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Level> levels;

    @ManyToOne
    private ScoreEngineTemplate scoreEngineTemplate;

    public ScoreEngine(){
        this.levels = new ArrayList<>();
    }

    public ScoreEngine(ScoreEngineTemplate scoreEngineTemplate) {
        this.name = "default";
        this.scoreEngineTemplate = scoreEngineTemplate;
        useTemplate();
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
        levels.sort((a,b) -> a.getRound() - b.getRound());
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

    private void useTemplate(){
        this.levels = new ArrayList<>();
        for(LevelTemplate levelTemplate : scoreEngineTemplate.getLevelTemplates()){
            List<LevelKnowledgeArea> levelKnowledgeAreas = new ArrayList<>();
            for(LevelKnowledgeAreaTemplate lkatemplate: levelTemplate.getLevelKnowledgeAreaTemplates()){
                LevelKnowledgeArea levelKnowledgeArea = new LevelKnowledgeArea();

                levelKnowledgeArea.setKnowledgeArea(lkatemplate.getKnowledgeArea());
                levelKnowledgeArea.setQuestion(lkatemplate.getQuestion());

                levelKnowledgeAreas.add(levelKnowledgeArea);
            }
            getLevels().add(new Level(levelTemplate.getName(), levelTemplate.getRound(), levelKnowledgeAreas));
        }
    }

    public void calculateScoresForCompleteGame(Game game){
        List<TeamLevelPrestation> teamLevelPrestations = new ArrayList<>();
        for (Team t: game.getTeams()){
            teamLevelPrestations.addAll(t.getTeamLevelPrestations());
        }
        for(Level l: game.getLevels()){
            if(l.isFinished() || l.isConcluded() || l.isCermonie()){
                calculateScores(teamLevelPrestations, l);
            }
        }
    }


    public void calculateScores(List<TeamLevelPrestation> teamLevelPrestations, Level level){
        CalculationStrategy calculationStrategy;
        resetScoresForLevel(teamLevelPrestations, level);
        for(LevelKnowledgeArea lka:  level.getLevelKnowledgeAreas()){
            for (TeamLevelPrestation tlp : teamLevelPrestations) {
                if(tlp.getLevel().getId() == level.getId()){
                    for(KnowledgeAreaScore kas: tlp.getKnowledgeAreaScores()){
                        if(kas.getKnowledgeArea().equals(lka.getKnowledgeArea())){
                            if(lka.getQuestion() != null){
                                if(lka.getQuestion().getAnswers().size() > 0){
                                    Question question = lka.getQuestion();
                                    if(question.getFormat().equals(ScoreFormat.RANGE) || question.getFormat().equals(ScoreFormat.AMOUNT_RANGE) || question.getFormat().equals(ScoreFormat.PERCENTAGE_RANGE)){
                                        calculationStrategy = new RangeCalculation();
                                    }
                                    else if(question.getFormat().equals(ScoreFormat.LIST)){
                                        calculationStrategy = new EnumeratioCalculation();
                                    }
                                    else if(question.getFormat().equals(ScoreFormat.NUMBER)){
                                        calculationStrategy = new IntCalculation();
                                    }
                                    else{
                                        calculationStrategy = new StringCalculation();
                                    }

                                    System.out.println(level.getName() + " " + lka.getKnowledgeArea().getName());
                                    calculationStrategy.calculateScore(kas, question);
                                } else{
                                    kas.setScore(0);
                                }
                            }
                            else{
                                kas.setScore(0);
                            }
                        }
                    }
                }
            }
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
                    if(question != null){
                        if(question.getFormat().equals(ScoreFormat.RANGE) || question.getFormat().equals(ScoreFormat.AMOUNT_RANGE) || question.getFormat().equals(ScoreFormat.PERCENTAGE_RANGE)){
                            calculationStrategy = new RangeCalculation();
                        }
                        else if(question.getFormat().equals(ScoreFormat.LIST)){
                            calculationStrategy = new EnumeratioCalculation();
                        }
                        else if(question.getFormat().equals(ScoreFormat.NUMBER)){
                            calculationStrategy = new IntCalculation();
                        }
                        else{
                            calculationStrategy = new StringCalculation();
                        }

                        calculationStrategy.calculateScore(knowledgeAreaScore, question);
                    }
                } else{
                    knowledgeAreaScore.setScore(0);
                }
            }
        }
    }

    public void resetScoresForLevel(List<TeamLevelPrestation> teamLevelPrestations, Level level){
        for(TeamLevelPrestation tlp: teamLevelPrestations){
            if(tlp.getLevel().getId() == level.getId()){
                for(KnowledgeAreaScore kas: tlp.getKnowledgeAreaScores()){
                    kas.setScore(0);
                }
            }
        }
    }

    public void resetScores(List<TeamLevelPrestation> teamLevelPrestations){
        for(TeamLevelPrestation tlp: teamLevelPrestations){
            for(KnowledgeAreaScore kas: tlp.getKnowledgeAreaScores()){
                kas.setScore(0);
            }
        }
    }

    public ScoreEngineTemplate getScoreEngineTemplate() {
        return scoreEngineTemplate;
    }

    public void setScoreEngineTemplate(ScoreEngineTemplate scoreEngineTemplate) {
        this.scoreEngineTemplate = scoreEngineTemplate;
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
