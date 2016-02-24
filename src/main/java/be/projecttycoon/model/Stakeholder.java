package be.projecttycoon.model;

import be.projecttycoon.model.level.Level;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ElementCollection
    private Set<Long> forbiddenUsers;

    @ElementCollection
    private List<String> links;

    public Stakeholder() {
        forbiddenUsers = new HashSet<>();
        links = new ArrayList<>();
    }

    public Stakeholder(String name, String imagePath,String description,String organisation,String function,int level) {
        this();
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.organisation = organisation;
        this.function = function;
        this.level = level;
    }

    public Stakeholder anonymous(){
        Stakeholder stakeholder =  new Stakeholder();
        stakeholder.setId(getId());
        stakeholder.setName("Unknown");
        stakeholder.setFunction("Unknown");
        stakeholder.setOrganisation("Unknown");
        stakeholder.setDescription("");
        stakeholder.setLevel(getLevel());
        stakeholder.setImagePath("img/unknown.png");
        return stakeholder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<Long> getForbiddenUsers() {
        return forbiddenUsers;
    }

    public void setForbiddenUsers(Set<Long> forbiddenUsers) {
        this.forbiddenUsers = forbiddenUsers;
    }

/**    public void addForbiddenUser(Team team){
        forbiddenUsers.add(team.getId());
    }*/

    public void addForbiddenUser(long userid){
        forbiddenUsers.add(userid);
    }

/**    public void removeForbiddenUser(Team team){
        forbiddenUsers.remove(team.getId());
    }*/

    public void removeForbiddenUser(long userid){
        forbiddenUsers.remove(userid);
    }

    public boolean allowUser(long userid){
        return !forbiddenUsers.contains(userid);
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
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
