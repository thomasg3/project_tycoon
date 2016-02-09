package be.projecttycoon.model;


import javax.validation.constraints.NotNull;


/**
 * Created by thomas on 08/02/16.
 */
public class Team {

    private int id;
    private String teamName,password;
    private String teamImage;
    private int score, likes;
    private boolean registered;

    //todo score, likes - errors/bean validation

    //constructors
    public Team(){}

    public Team(int id,String teamName, String password, String path){
        setId(id);
        setTeamName(teamName);
        setPassword(password);
        setTeamImage(path);
        setRegistered(false);
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setTeamImage(String path){
        if(path!=null)
        //this.teamImage = ImageIO.read(new File(path));
        this.teamImage=path;
    }
    public void setTeamName(@NotNull String teamName) {
        this.teamName = teamName;
    }
    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public void setRegistered(boolean registered){
        this.registered=registered;
    }

    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getPassword() {
        return password;
    }

    public String getTeamImage() {
        return teamImage;
    }
    public boolean isRegistered(){
        return this.registered;
    }

    public void register(String password,String path){
        setPassword(password);
        setTeamImage(path);
        setRegistered(true);
    }
}
