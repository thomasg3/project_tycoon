package be.projecttycoon.rest.util;

/**
 * Created by Jeroen on 22-2-2016.
 */
public class ScoreEngineLimitBean {

    private long id;
    private int levels;
    private String name;

    public ScoreEngineLimitBean(){}

    public ScoreEngineLimitBean(long id, int levels, String name) {
        this.id = id;
        this.levels = levels;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
