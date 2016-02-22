package be.projecttycoon.model;


import be.projecttycoon.model.level.Level;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by thomas on 08/02/16.
 */

@Entity
public class Team{

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 5, message = "Your teamname must be at least 5 characters")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message="Your teamname can only contain characters")
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.RELAXED, message = "Please enter a name that compliance with html url standards")
    private String teamname;

    @NotNull
    @Size(min = 6, message = "Your password must be at least 6 characters")
    @JsonIgnore
    private String password;

    private String teamImage;
    private String email;

    private int likes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamLevelPrestation> teamLevelPrestations;

    private TeamState state;


    //todo errors/bean validation

    //constructors
    public Team() {
        state = TeamState.UNREGISTERED;
        teamLevelPrestations = new ArrayList<>();
    }

    public Team(String teamname, String password, List<Level> levels){
        this();
        setPassword(password);
        setTeamname(teamname);
        for(Level level : levels){
            teamLevelPrestations.add(new TeamLevelPrestation(level));
        }
    }

    public Team(String teamName, String password, List<Level> levels ,String path){
        this(teamName, password, levels);
        setTeamImage(path);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        this.password = passwordEncoder.encode(password);
    }

    public String getTeamImage() {
        return teamImage;
    }
    public void setTeamImage(String path){
        if(path!=null)
        this.teamImage=path;
    }


    public String getTeamname() {
        return teamname;
    }
    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public int getScore() {
        return teamLevelPrestations.stream().map(tlp -> tlp.getLevelScore()).reduce(0, (x,y) -> x + y);
    }

    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }


    public boolean isRegistered(){
        return state != TeamState.UNREGISTERED;
    }

    public TeamState getState() {
        return state;
    }
    public void setRegistered(boolean registered){
        if(registered && state == TeamState.UNREGISTERED)
            state = TeamState.TEAM;
    }

    public boolean isAdmin(){
        return state == TeamState.ADMIN;
    }
    public void setAdmin(boolean admin){
        if(admin)
            state = TeamState.ADMIN;
    }

    public List<TeamLevelPrestation> getTeamLevelPrestations() {
        return teamLevelPrestations;
    }

    @JsonIgnore
    public List<TeamLevelPrestation> getPublicTeamLevelPrestations(){
        return getTeamLevelPrestations().stream().map(
                tlp -> {
                    if(tlp.getLevel().teamsCanSeePoints()){
                        return tlp;
                    } else {
                        tlp.setKnowledgeAreaScores(tlp.getKnowledgeAreaScores().stream().map(kas -> {
                            kas.setScore(0);
                            kas.setAnswer("");
                            return kas;
                        }).collect(Collectors.toList()));
                        return tlp;
                    }
                }
        ).collect(Collectors.toList());
    }


    @JsonIgnore
    public List<TeamLevelPrestation> getOwnTeamLevelPrestations() {
        return getTeamLevelPrestations().stream().map(
                tlp -> {
                    if(tlp.getLevel().teamsCanSeePoints()){
                        return tlp;
                    } else {
                        tlp.setKnowledgeAreaScores(tlp.getKnowledgeAreaScores().stream().map(kas -> {
                            kas.setScore(0);
                            return kas;
                        }).collect(Collectors.toList()));
                        return tlp;
                    }
                }
        ).collect(Collectors.toList());
    }

    public void setTeamLevelPrestations(List<TeamLevelPrestation> teamLevelPrestations) {
        this.teamLevelPrestations = teamLevelPrestations;
    }

    public void register(String password, String teamname, String email){
        setTeamname(teamname);
        setPassword(password);
        setEmail(email);
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
                ", score=" + getScore() +
                ", likes=" + likes +
                ", registered=" + isRegistered() +
                '}';
    }


}
