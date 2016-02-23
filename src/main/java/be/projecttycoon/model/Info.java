package be.projecttycoon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by michael on 18/02/16.
 */
@Entity
public class Info {

    //todo choose which team can see it

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private long id;
    @Min(value = 1, message = "The level must be at least 1")
    private int unlockedAtLevel;
    private String description;
    private String path;
    private InfoType type;

    @ElementCollection
    private Set<Long> excludedTeams;

   public Info() {
        this(1,"","",InfoType.Document);
    }

    public Info(int unlockedAtLevel, String description, String path, InfoType type) {
        this(unlockedAtLevel,description,path,type,new HashSet<>());
    }
    public Info(int unlockedAtLevel,String description,String path,InfoType type,Set<Long> excludedTeams){
        this.unlockedAtLevel = unlockedAtLevel;
        this.description = description;
        this.path = path;
        this.type = type;
        this.excludedTeams=excludedTeams;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public InfoType getType() {
        return type;
    }

    public void setType(InfoType type) {
        this.type = type;
    }

    public int getUnlockedAtLevel() {
        return unlockedAtLevel;
    }

    public void setUnlockedAtLevel(int unlockedAtLevel) {
        this.unlockedAtLevel = unlockedAtLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getExcludedTeams() {
        return excludedTeams;
    }

    public void setExcludedTeams(Set<Long> excludedTeams) {
        this.excludedTeams = excludedTeams;
    }

    public void addTeamToBlackList(long id){
        excludedTeams.add(id);
    }
    public void removeTeamFromBlackList(long id){
        excludedTeams.remove(id);
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", unlockedAtLevel=" + unlockedAtLevel +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", type=" + type +
                '}';
    }
}
