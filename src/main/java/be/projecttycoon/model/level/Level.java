package be.projecttycoon.model.level;


import be.projecttycoon.model.LevelKnowledgeArea;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by thomas on 11/02/16.
 */
@Entity
public class Level{

    @Id
    @GeneratedValue
    private long id;

    @Min(value = 0, message = "The round number needs to be higher than 0")
    private int round;
    @NotNull
    @Size(min = 6, message = "The name of the knowledge area must be at least 6 characters long")
    @Pattern(regexp = "^[A-Za-z0-9\\s]*$", message="Your levelname can only contain characters, numbers and spaces")
    private String name;

    @OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    private List<LevelKnowledgeArea> levelKnowledgeAreas;

    @Transient
    private LevelState levelState;
    private String state;

    public Level() {
        this.levelState = new Closed(this);
        this.state = levelState.getClass().getSimpleName();
        this.levelKnowledgeAreas = new ArrayList<>();
    }

    public Level(String name, int round, List<LevelKnowledgeArea> levelKnowledgeAreas) {
        this();
        this.name = name;
        this.round = round;
        this.levelKnowledgeAreas = levelKnowledgeAreas;
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

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public List<LevelKnowledgeArea> getLevelKnowledgeAreas() {
        return levelKnowledgeAreas;
    }

    @Transient
    @JsonIgnore
    public List<LevelKnowledgeArea> getPublicKnowledgeAreas(){
        if(isClosed()){
            return levelKnowledgeAreas.stream()
                    .map(lkas -> {lkas.setQuestion(null); return lkas;})
                    .collect(Collectors.toList());
        } else {
            return (List<LevelKnowledgeArea>)levelKnowledgeAreas.stream()
                    .map(lkas -> {
                        lkas.getQuestion().setAnswers(Collections.EMPTY_LIST);
                        return lkas;
                    })
                    .collect(Collectors.toList());
        }
    }

    public void setLevelKnowledgeAreas(List<LevelKnowledgeArea> levelKnowledgeAreas) {
        this.levelKnowledgeAreas = levelKnowledgeAreas;
    }

    public String getState(){
        this.state = levelState.getClass().getSimpleName();
        return state;
    }

    public void setState(String state){
        this.state = state;
        updateState();
    }

    @PostLoad
    public void updateState(){
        if(Open.class.getSimpleName().equals(state)){
            this.levelState = new Open(this);
        } else if(Finished.class.getSimpleName().equals(state)){
            this.levelState = new Finished(this);
        } else if(Cermonie.class.getSimpleName().equals(state)){
            this.levelState = new Cermonie(this);
        } else if(Concluded.class.getSimpleName().equals(state)){
            this.levelState = new Concluded(this);
        } else {
            this.levelState = new Closed(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Level)) return false;

        Level level = (Level) o;

        if (getId() != level.getId()) return false;
        if (getRound() != level.getRound()) return false;
        if (getName() != null ? !getName().equals(level.getName()) : level.getName() != null) return false;
        return getLevelKnowledgeAreas() != null ? getLevelKnowledgeAreas().equals(level.getLevelKnowledgeAreas()) : level.getLevelKnowledgeAreas() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getRound();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getLevelKnowledgeAreas() != null ? getLevelKnowledgeAreas().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Level{" +
                "id=" + id +
                ", round=" + round +
                ", name='" + name + '\'' +
                ", levelKnowledgeAreas=" + levelKnowledgeAreas +
                '}';
    }

    public void openUp() {
        levelState.open();
    }
    public void closeUp() {
        levelState.close();
    }
    public void pointPush() {
        levelState.pointPush();
    }
    public void cermonieFinished() {
        levelState.cermonieFinished();
    }


    public boolean isClosed(){
        return levelState instanceof Closed;
    }
    public boolean isOpen(){
        return levelState instanceof Open;
    }
    public boolean isFinished(){
        return levelState instanceof Finished;
    }
    public boolean isCermonie(){
        return levelState instanceof Cermonie;
    }
    public boolean isConcluded(){
        return levelState instanceof Concluded;
    }

    public boolean teamsCanSeePoints(){
        return levelState.teamsCanSeePoints();
    }
    public boolean documentsAreOpen(){
        return levelState.documentsAreOpen();
    }
    public boolean questionsAreVisible(){
        return levelState.questionsAreVisible();
    }
    public boolean questionsAreOpen(){
        return levelState.questionsAreOpen();
    }




}
