package be.projecttycoon.model;


import javax.validation.constraints.NotNull;
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
    private boolean registered;

    //todo errors/bean validation

    //constructors
    public Team() {super();}

    public Team(String teamname, String password){
        setPassword(password);
        setTeamname(teamname);
    }

    public Team(String teamName, String password, String path){
        setPassword(password);
        setTeamname(teamName);
        setTeamImage(path);
        setRegistered(false);
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


    public void setScore(int score) {
        this.score = score;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setTeamImage(String path){
        if(path!=null)
        this.teamImage=path;
    }

    public void setRegistered(boolean registered){
        this.registered=registered;
    }


    public String getTeamImage() {
        return teamImage;
    }
    public boolean isRegistered(){
        return this.registered;
    }

    public int getScore() {
        return score;
    }

    public int getLikes() {
        return likes;
    }

    public void register(String password, String teamname, String path){
        setTeamname(teamname);
        setPassword(password);
        setTeamImage(path);
        setRegistered(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;

        if(team.getId()==this.getId()&&team.getTeamname().equals(this.getTeamname())){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = getTeamImage() != null ? getTeamImage().hashCode() : 0;
        result = 31 * result + getScore();
        result = 31 * result + getLikes();
        result = 31 * result + (isRegistered() ? 1 : 0);
        return result;
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
                ", registered=" + registered +
                '}';
    }
}
