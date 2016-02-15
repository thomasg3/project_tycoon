package be.projecttycoon.model;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by thomas on 11/02/16.
 */
@Entity
public class Level {

    @Id
    @GeneratedValue
    private long id;

    @Min(value = 0, message = "The round number needs to be higher than 0")
    private int round;
    @NotNull
    @Size(min = 6, message = "The name of the knowledge area must be at least 6 characters long")
    @Pattern(regexp = "^[A-Za-z0-9\\s]*$", message="Your levelname can only contain characters, numbers and spaces")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @ManyToMany
    private List<KnowledgeArea> knowledgeAreas;

    public Level() {
    }

    public Level(String name, int round, List<KnowledgeArea> knowledgeAreas) {
        this.name = name;
        this.round = round;
        this.knowledgeAreas = knowledgeAreas;
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

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public List<KnowledgeArea> getKnowledgeAreas() {
        return knowledgeAreas;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setKnowledgeAreas(List<KnowledgeArea> knowledgeAreas) {
        this.knowledgeAreas = knowledgeAreas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Level)) return false;

        Level level = (Level) o;

        if (getId() != level.getId()) return false;
        if (getRound() != level.getRound()) return false;
        if (getName() != null ? !getName().equals(level.getName()) : level.getName() != null) return false;
        if (questions != null ? !questions.equals(level.questions) : level.questions != null) return false;
        return getKnowledgeAreas() != null ? getKnowledgeAreas().equals(level.getKnowledgeAreas()) : level.getKnowledgeAreas() == null;

    }
}
