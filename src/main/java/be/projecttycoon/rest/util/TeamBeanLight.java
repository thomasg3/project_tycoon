package be.projecttycoon.rest.util;

/**
 * Created by Jeroen on 23-2-2016.
 */
public class TeamBeanLight {

    private long id;
    private String teamname;
    private int numberOfAnswers;

    public TeamBeanLight(){};

    public TeamBeanLight(long id, String teamname, int numberOfAnswers) {
        this.id = id;
        this.teamname = teamname;
        this.numberOfAnswers = numberOfAnswers;
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

    public int getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public void setNumberOfAnswers(int numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }
}
