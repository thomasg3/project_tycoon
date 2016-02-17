package be.projecttycoon.model;

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
}
