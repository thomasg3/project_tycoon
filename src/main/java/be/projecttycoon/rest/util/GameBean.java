package be.projecttycoon.rest.util;

/**
 * Created by thomas on 10/02/16.
 */
public class GameBean {
    private String gameName;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
