package be.projecttycoon.model.level;


import be.projecttycoon.model.LevelKnowledgeArea;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    private List<LevelKnowledgeArea> levelKnowledgeAreas;

    @Transient
    private LevelState state;

    public Level() {
        this.state = new Closed(this);
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

    public void setLevelKnowledgeAreas(List<LevelKnowledgeArea> levelKnowledgeAreas) {
        this.levelKnowledgeAreas = levelKnowledgeAreas;
    }

    void setState(LevelState state){
        this.state = state;
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

    public void cermonieFinished() {
        state.cermonieFinished();
    }

    public void open() {
        state.open();
    }

    public void close() {
        state.close();
    }

    public void pointPush() {
        state.pointPush();
    }

    public boolean isClosed(){
        return state instanceof Closed;
    }

    public void setClosed(boolean closed){
        if(closed)
            state = new Closed(this);
    }

    public boolean isOpen(){
        return state instanceof Open;
    }

    public void setOpen(boolean open){
        if(open)
            state = new Open(this);
    }

    public boolean isFinished(){
        return state instanceof Finished;
    }

    public void setFinished(boolean finished){
        if(finished)
            state = new Finished(this);
    }

    public boolean isCermonie(){
        return state instanceof Cermonie;
    }

    public void setCermonie(boolean cermonie){
        if(cermonie)
            state = new Cermonie(this);
    }

    public boolean isConcluded(){
        return state instanceof Concluded;
    }

    public void setConcluded(boolean concluded){
        if(concluded)
            state = new Concluded(this);
    }

    public boolean teamsCanSeePoints(){
        return state.teamsCanSeePoints();
    }
    public boolean documentsAreOpen(){
        return state.documentsAreOpen();
    }
    public boolean questionsAreOpen(){
        return state.questionsAreOpen();
    }
}
