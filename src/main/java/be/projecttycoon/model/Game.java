package be.projecttycoon.model;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by michael on 09/02/16.
 */
@Entity
public class Game {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<Team> teams;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Level> levels;

    private static int count = 1;


    public Game() {
        this.teams = new HashSet<>();
        this.levels = new ArrayList<>();
    }

    public Game(String name, int teams, int levels, List<KnowledgeArea> knowledgeAreas) {
        this();
        setName(name);
        generateGame(teams, levels, knowledgeAreas);

    }

    private void generateGame(int teams, int levels, List<KnowledgeArea> knowledgeAreas){
        for(int i = 1; i<=levels; i++){
            this.levels.add(new Level("Level "+ i, i, knowledgeAreas));
        }
        for(int i = count; count<i + teams;count++){
            this.teams.add(new Team("Team"+(count),"ThisIsTheMostAwesomePasswordEver",this.levels,"http://i.imgur.com/IhewUTH.jpg"));
        }

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
                '}';
    }
}
