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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
