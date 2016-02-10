package be.projecttycoon.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Created by thomas on 08/02/16.
 */

@Entity
public class Team{

    @Id
    @GeneratedValue
    private long id;

    private String teamname;
    private String password;

    private String teamImage;
    private int score, likes;
    private TeamState state;

    //todo errors/bean validation

    //constructors
    public Team() {
        state = TeamState.UNREGISTERED;
    }

    public Team(String teamname, String password){
        this();
        setPassword(password);
        setTeamname(teamname);
    }

    public Team(String teamName, String password, String path){
        this(teamName, password);
        setTeamImage(path);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getTeamImage() {
        return teamImage;
    }

    public void setTeamImage(String path){
        if(path!=null)
        this.teamImage=path;
    }

    public boolean isRegistered(){
        return state != TeamState.UNREGISTERED;
    }

    public void setRegistered(boolean registered){
        if(state == TeamState.UNREGISTERED)
            state = TeamState.TEAM;
    }

    public boolean isAdmin(){
        return state == TeamState.ADMIN;
    }

    public void setAdmin(boolean admin){
        state = TeamState.ADMIN;
    }

    public void register(String password, String teamname, String path){
        setTeamname(teamname);
        setPassword(password);
        setTeamImage(path);
        setRegistered(true);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamname='" + teamname + '\'' +
                ", password='" + password + '\'' +
                ", teamImage='" + teamImage + '\'' +
                ", score=" + score +
                ", likes=" + likes +
                ", registered=" + isRegistered() +
                '}';
    }
}
