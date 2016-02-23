package be.projecttycoon.rest.util;

/**
 * Created by thomas on 23/02/16.
 */
public class ShortGameBean {
    private long id;
    private String name;

    public ShortGameBean() {
    }

    public ShortGameBean(long id, String name) {
        this.id = id;
        this.name = name;
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
