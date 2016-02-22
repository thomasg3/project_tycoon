package be.projecttycoon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

/**
 * Created by michael on 18/02/16.
 */
@Entity
public class Info {

    //todo choose which team can see it -- here? (seems strange) or team keeps its own info (team is getting pretty big already..)
    //link with scoreEngine? if so how do i choose which engine? there is only one at the moment but in the future it might change
    @Id
    @GeneratedValue
    private long id;
    @Min(value = 1, message = "The level must be at least 1")
    private int unlockedAtLevel;
    private String description;
    private String path;
    private InfoType type;

    public Info() {
        //not sure if we even want this but ok
        this(1,"","",InfoType.Document);
    }

    public Info(int unlockedAtLevel, String description, String path, InfoType type) {
        this.unlockedAtLevel = unlockedAtLevel;
        this.description = description;
        this.path = path;
        this.type = type;
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
}
