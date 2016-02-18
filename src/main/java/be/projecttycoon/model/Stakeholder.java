package be.projecttycoon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kiwi on 17/02/2016.
 */

@Entity
public class Stakeholder {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String imagePath;

    public Stakeholder(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public Stakeholder() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stakeholder that = (Stakeholder) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        return imagePath.equals(that.imagePath);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + imagePath.hashCode();
        return result;
    }
}
