package be.projecttycoon.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by thomas on 11/02/16.
 */
@Entity
public class KnowledgeArea {

    @Id
    @NotNull(message = "Please enter a name for the knowledge area")
    @Size(min=6, message="Minimum length is 6 characters")
    @Pattern(regexp = "^[A-Za-z0-9\\s]*$", message = "The knowledge area can only contain characters, numbers and spaces")
    private String name;

    @Min(value = 0, message = "Enter a value higher than 0")
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
