package be.projecttycoon.model;

import javax.imageio.ImageIO;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by thomas on 08/02/16.
 */
public class Team {

    private int id;
    private String teamName,password;
    private Image teamImage;
    private boolean registered;

    //constructors
    public Team(){}

    public Team(int id,String teamName, String password, String path) throws IOException{
        setId(id);
        setTeamName(teamName);
        setPassword(password);
        setTeamImage(path);
        setRegistered(false);
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setTeamImage(String path) throws IOException{

        this.teamImage = ImageIO.read(new File(path));
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

    public Image getTeamImage() {
        return teamImage;
    }
    public boolean isRegistered(){
        return this.registered;
    }

    public void register(String password,String path) throws IOException{
        setPassword(password);
        setTeamImage(path);
        setRegistered(true);
    }
}
