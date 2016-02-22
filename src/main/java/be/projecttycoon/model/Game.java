package be.projecttycoon.model;

import be.projecttycoon.model.ScoreEngine.ScoreEngine;
import be.projecttycoon.model.level.Level;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Created by michael on 09/02/16.
 */
@Entity
public class Game {

    @Id
    @GeneratedValue
    private long id;
    @NotNull(message = "Please enter a gamename")
    @Size(min = 4, message = "Your gamename must be at least 4 characters")
    @Pattern(regexp = "^[A-Za-z0-9\\s]*$", message="Your gamename can only contain characters and numbers")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<Team> teams;

    @OneToOne(cascade = CascadeType.ALL)
    private ScoreEngine scoreEngine;

    private static int count = 1;


    public Game() {
        this.teams = new HashSet<>();
        this.scoreEngine = new ScoreEngine();
    }

    public Game(String name, int teams, ScoreEngine scoreEngine){
        this();
        this.teams = new HashSet<>();
        this.scoreEngine = scoreEngine;
        setName(name);
        generateTeam(teams);
    }

    /*
    public Game(String name, int teams, int levels, List<KnowledgeArea> knowledgeAreas) {
        this();
        this.scoreEngine = new ScoreEngine();
        setName(name);
        generateGame(teams, levels, knowledgeAreas);
    }
    */

    private void generateTeam(int teams){
        System.out.println(getScoreEngine().toString());
        for(int i = count; count<i + teams;count++){
            this.teams.add(new Team("Team"+(count),"testtest",this.scoreEngine.getLevels(),"/hosted_resources/admin_1455635149425.png"));
        }
    }

    private void generateGame(int teams, int levels, List<KnowledgeArea> knowledgeAreas){
        for(int i = 1; i<=levels; i++){
            List<LevelKnowledgeArea> levelKnowledgeAreas = new ArrayList<>();
            for (KnowledgeArea k:knowledgeAreas){
                LevelKnowledgeArea lk = new LevelKnowledgeArea();
                lk.setKnowledgeArea(k);
                levelKnowledgeAreas.add(lk);
            }
            this.scoreEngine.getLevels().add(new Level("Level "+ i, i, levelKnowledgeAreas));
        }
        for(int i = count; count<i + teams;count++){
            this.teams.add(new Team("Team"+(count),"testtest",this.scoreEngine.getLevels(),"/hosted_resources/admin_1455635149425.png"));
        }
    }

    @Transient
    public List<Level> getLevels(){
        return this.scoreEngine.getLevels();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ScoreEngine getScoreEngine() {
        return scoreEngine;
    }

    public void setScoreEngine(ScoreEngine scoreEngine) {
        this.scoreEngine = scoreEngine;
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public boolean containsTeam(Team t){
        return teams.contains(t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;

        Game game = (Game) o;

        return getId() == game.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teams=" + teams +
                ", scoreEngine=" + scoreEngine +
                '}';
    }

    public void calculateScores(List<TeamLevelPrestation> teamLevelPrestations, List<LevelKnowledgeArea> levelKnowledgeAreas){
        //scoreengine....
    }
}
