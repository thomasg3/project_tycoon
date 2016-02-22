package be.projecttycoon.rest.util;

/**
 * Created by thomas on 10/02/16.
 */
public class GameBean {
    private String name;
    private int amount;
    private long scoreengineid;

    public GameBean(){}

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
