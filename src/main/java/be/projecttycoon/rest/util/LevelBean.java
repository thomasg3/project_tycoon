package be.projecttycoon.rest.util;

import be.projecttycoon.model.level.LevelState;

/**
 * Created by kiwi on 19/02/2016.
 */
public class LevelBean {
    private long id;
    private String name;
    private String state;

    public LevelBean(){}

    public LevelBean(long id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
