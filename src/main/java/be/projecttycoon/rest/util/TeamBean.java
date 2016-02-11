package be.projecttycoon.rest.util;

import be.projecttycoon.model.TeamState;

/**
 * Created by thomas on 10/02/16.
 */
public class TeamBean {
    private long id;

    private String teamname;
    private String password;
    private String teamImage;
    private int score, likes;
    private TeamState state;

    public TeamBean(){}

    public TeamBean(long id, String teamname, String password, String teamImage, int score, int likes, TeamState state) {
        this.id = id;
        this.teamname = teamname;
        this.password = password;
        this.teamImage = teamImage;
        this.score = score;
        this.likes = likes;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeamImage() {
        return teamImage;
    }

    public void setTeamImage(String teamImage) {
        this.teamImage = teamImage;
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

    public TeamState getState() {
        return state;
    }

    public void setState(TeamState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "TeamBean{" +
                "id=" + id +
                ", teamname='" + teamname + '\'' +
                ", password='" + password + '\'' +
                ", teamImage='" + teamImage + '\'' +
                ", score=" + score +
                ", likes=" + likes +
                ", state=" + state +
                '}';
    }
}
