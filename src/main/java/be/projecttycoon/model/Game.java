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
    //gametype?


    public Game() {
    }

    public Game(String name, int teams) {
        setName(name);
        this.teams = new HashSet<Team>(teams);
        for(int i=0;i<teams;i++){
            Team t = new Team("Team"+(i+1),"ThisIsTheMostAwesomePasswordEver","http://i.imgur.com/IhewUTH.jpg");



            this.teams.add(t);
        }
    }

    public void setName(String name) {
        try {
            URL url = new URL("http://www.google.com/" + name);
            this.name = name;
        }catch(MalformedURLException ex){
            //todo throw or catch
            System.out.println("not a valid name");
        }

    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
