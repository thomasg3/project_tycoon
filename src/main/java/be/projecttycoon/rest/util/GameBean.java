package be.projecttycoon.rest.util;

import be.projecttycoon.model.ScoreEngineTemplate.ScoreEngineTemplate;
import be.projecttycoon.model.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 10/02/16.
 */
public class GameBean {
    private String name;
    private int amount;
    private int scoreengineid;

    public GameBean() {
    }

    public GameBean(String name, int amount, int scoreengineid) {
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

    public int getScoreengineid() {
        return scoreengineid;
    }

    public void setScoreengineid(int scoreengineid) {
        this.scoreengineid = scoreengineid;
    }
}
