package be.projecttycoon.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by thomas on 11/02/16.
 */
@Entity
public class KnowledgeArea {
    @Id
    private String name;

    private int elementNumber;

    public KnowledgeArea() {
    }

    public KnowledgeArea(String name, int elementNumber) {
        this.name = name;
        this.elementNumber = elementNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElementNumber() {
        return elementNumber;
    }

    public void setElementNumber(int elementNumber) {
        this.elementNumber = elementNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowledgeArea)) return false;

        KnowledgeArea that = (KnowledgeArea) o;

        return getName().equals(that.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
