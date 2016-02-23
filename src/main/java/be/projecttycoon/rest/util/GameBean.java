package be.projecttycoon.rest.util;

import be.projecttycoon.model.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 10/02/16.
 */
public class GameBean {
    private String name;
    private int amount;
    private long scoreengineid;
    private int levels;
    private List<LevelBean> allLevels;


    public GameBean(){
        allLevels=new ArrayList<LevelBean>();
    }

    public List<LevelBean> getAllLevels() {
        return allLevels;
    }

    public void setAllLevels(List<LevelBean> allLevels) {
        this.allLevels = allLevels;
    }



    public GameBean(String name, int amount, long scoreengineid) {
        this.name = name;
        this.amount = amount;
        this.scoreengineid = scoreengineid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getScoreengineid() {
        return scoreengineid;
    }

    public void setScoreengineid(long scoreengineid) {
        this.scoreengineid = scoreengineid;
    }
}
