package be.projecttycoon.model;


import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


/**
 * Created by thomas on 08/02/16.
 */
@Entity
public class Team extends User{


    private String teamImage;
    private int score, likes;
    private boolean registered;

    //todo errors/bean validation

    //constructors
    public Team(){
        super();
    }

    public Team(String teamName, String password, String path){
        super(password,teamName);
        setTeamImage(path);
        setRegistered(false);
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

    public void register(String password, String path){
        setPassword(password);
        setTeamImage(path);
        setRegistered(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;

        if(team.getId()==this.getId()){
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
}
