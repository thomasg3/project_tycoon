package be.projecttycoon.model;

import be.projecttycoon.model.ScoreEngine.ScoreEngine;
import be.projecttycoon.model.level.Level;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

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

    @ManyToOne
    private ScoreEngine scoreEngine;

    private static int count = 1;


    public Game() {
        this.teams = new HashSet<>();
    }

    public Game(String name, int teams, ScoreEngine scoreEngine){
        this();
        this.teams = new HashSet<>();
        this.scoreEngine = scoreEngine;
        setName(name);
        generateTeam(teams);
    }

    private void generateTeam(int teams){
        for(int i = count; count<i + teams;count++){
            this.teams.add(new Team("Team"+(count),"testtest",this.scoreEngine.getLevels(),"/hosted_resources/admin_1455635149425.png"));
        }
    }

    public void sanitizeFor(String username){
        getScoreEngine().setLevels(
                getScoreEngine().getLevels().stream()
                        .map(level -> {
                            level.setLevelKnowledgeAreas(level.getPublicKnowledgeAreas());
                            return level;

                        })
                        .collect(Collectors.toList())
        );
        getTeams().stream().forEach(t -> {
            if(!t.getTeamname().equals(username)){
                t.setTeamLevelPrestations(t.getPublicTeamLevelPrestations());
            } else {
                t.setTeamLevelPrestations(t.getOwnTeamLevelPrestations());
            }
        });
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

    public int openLevel(){
        return getLevels().stream()
                .filter(l -> l.documentsAreOpen())
                .map(l -> l.getRound())
                .max(Integer::compareTo)
                .orElse(0);
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
