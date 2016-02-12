package be.projecttycoon.rest.util;

/**
 * Created by thomas on 10/02/16.
 */
public class GameBean {
    private String name;
    private int amount;
    private int levels;

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
