package be.projecttycoon.model;

import be.projecttycoon.model.level.Level;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kiwi on 17/02/2016.
 */

@Entity
public class Stakeholder {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String imagePath;
    private String organisation;
    private String function;
    private String description;
    private int level;

    public Stakeholder(String name, String imagePath,String description,String organisation,String function,int level) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.organisation = organisation;
        this.function = function;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Stakeholder() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stakeholder that = (Stakeholder) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
