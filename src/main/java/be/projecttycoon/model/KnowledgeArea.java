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

    public KnowledgeArea() {
    }

    public KnowledgeArea(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
