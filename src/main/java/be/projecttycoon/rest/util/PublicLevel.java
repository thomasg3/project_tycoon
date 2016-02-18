package be.projecttycoon.rest.util;

import be.projecttycoon.model.level.Level;
import be.projecttycoon.model.LevelKnowledgeArea;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 17-2-2016.
 */
public class PublicLevel {


    private long id;
    private int round;
    private String name;
    private List<LevelKnowledgeArea> levelKnowledgeAreas;

    public PublicLevel() {
        this.levelKnowledgeAreas = new ArrayList<>();
    }

    public PublicLevel(Level level){
        this.id = level.getId();
        this.round = level.getRound();
        this.name = level.getName();
        levelKnowledgeAreas = level.getLevelKnowledgeAreas();
    }

    public PublicLevel(long id, int round, String name, List<LevelKnowledgeArea> levelKnowledgeAreas) {
        this.id = id;
        this.round = round;
        this.name = name;
        this.levelKnowledgeAreas = levelKnowledgeAreas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LevelKnowledgeArea> getLevelKnowledgeAreas() {
        return levelKnowledgeAreas;
    }

    public void setLevelKnowledgeAreas(List<LevelKnowledgeArea> levelKnowledgeAreas) {
        this.levelKnowledgeAreas = levelKnowledgeAreas;
    }
}
