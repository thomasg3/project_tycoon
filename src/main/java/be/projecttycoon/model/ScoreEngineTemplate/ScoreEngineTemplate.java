package be.projecttycoon.model.ScoreEngineTemplate;

import be.projecttycoon.model.KnowledgeArea;
import be.projecttycoon.model.LevelKnowledgeArea;
import be.projecttycoon.model.level.Level;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 23-2-2016.
 */
@Entity
public class ScoreEngineTemplate {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LevelTemplate> levelTemplates;

    public ScoreEngineTemplate(String name, List<LevelTemplate> levelTemplates) {
        this.name = name;
        this.levelTemplates = levelTemplates;
    }

    public ScoreEngineTemplate(String name, int levels, List<KnowledgeArea> knowledgeAreas) {
        this.name = name;
        generateLevels(levels, knowledgeAreas);
    }

    public ScoreEngineTemplate() {


    }

    private void generateLevels(int levels, List<KnowledgeArea> knowledgeAreas){
        this.levelTemplates = new ArrayList<>();
        for(int i = 1; i<=levels; i++){
            List<LevelKnowledgeAreaTemplate> levelKnowledgeAreas = new ArrayList<>();
            for (KnowledgeArea k:knowledgeAreas){
                LevelKnowledgeAreaTemplate lk = new LevelKnowledgeAreaTemplate();
                lk.setKnowledgeArea(k);
                levelKnowledgeAreas.add(lk);
            }
            getLevelTemplates().add(new LevelTemplate("Level "+ i, i, levelKnowledgeAreas));
        }
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

    public List<LevelTemplate> getLevelTemplates() {
        return levelTemplates;
    }

    public void setLevelTemplates(List<LevelTemplate> levelTemplates) {
        this.levelTemplates = levelTemplates;
    }
}
