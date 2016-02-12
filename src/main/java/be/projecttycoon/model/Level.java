package be.projecttycoon.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * Created by thomas on 11/02/16.
 */
@Entity
public class Level {

    @Id
    @GeneratedValue
    private long id;


    private int round;
    private String name;

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

    public void setKnowledgeAreas(List<KnowledgeArea> knowledgeAreas) {
        this.knowledgeAreas = knowledgeAreas;
    }
}
