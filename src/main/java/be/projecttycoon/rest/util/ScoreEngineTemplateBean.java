package be.projecttycoon.rest.util;

import be.projecttycoon.model.KnowledgeArea;

import java.util.List;

/**
 * Created by Jeroen on 23-2-2016.
 */
public class ScoreEngineTemplateBean {

    private String name;
    private int levels;

    public ScoreEngineTemplateBean() {
    }

    public ScoreEngineTemplateBean(String name, int levels) {
        this.name = name;
        this.levels = levels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }
}
