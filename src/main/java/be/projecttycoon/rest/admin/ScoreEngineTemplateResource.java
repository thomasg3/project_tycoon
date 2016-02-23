package be.projecttycoon.rest.admin;

import be.projecttycoon.db.KnowledgeAreaRepository;
import be.projecttycoon.db.ScoreEngineTemplateRepository;
import be.projecttycoon.model.ScoreEngine.ScoreEngine;
import be.projecttycoon.model.ScoreEngineTemplate.ScoreEngineTemplate;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.util.ScoreEngineTemplateBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Jeroen on 23-2-2016.
 */

@RestController
@RequestMapping("/api/admin/scoreEngineTemplate")
public class ScoreEngineTemplateResource {
    ScoreEngineTemplateRepository scoreEngineTemplateRepository;
    KnowledgeAreaRepository knowledgeAreaRepository;

    @Autowired
    public ScoreEngineTemplateResource(ScoreEngineTemplateRepository scoreEngineTemplateRepository, KnowledgeAreaRepository knowledgeAreaRepository) {
        this.scoreEngineTemplateRepository = scoreEngineTemplateRepository;
        this.knowledgeAreaRepository = knowledgeAreaRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ScoreEngineTemplate> getAllScoreEngineTemplates(){
        List<ScoreEngineTemplate> scoreEnginesTemplates =  scoreEngineTemplateRepository.findAll();
        return scoreEnginesTemplates;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public ScoreEngineTemplate getScoreEngineTemplates(@PathVariable long id ){
        ScoreEngineTemplate scoreEngineTemplate = scoreEngineTemplateRepository.findOne(id);
        if(scoreEngineTemplate == null)
            throw new NotFoundException();
        return scoreEngineTemplate;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Produces("application/json")
    public ScoreEngineTemplate createScoreEngineTemplate(@RequestBody ScoreEngineTemplateBean scoreEngineTemplateBean){
        ScoreEngineTemplate scoreEngineTemplate = new ScoreEngineTemplate(scoreEngineTemplateBean.getName(), scoreEngineTemplateBean.getLevels(),knowledgeAreaRepository.findAll() );
        if(scoreEngineTemplate==null)
            throw new NotFoundException();
        return scoreEngineTemplateRepository.save(scoreEngineTemplate);
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
    @Produces("application/json")
    public List<ScoreEngineTemplate> deleteScoreEngine(@PathVariable long id){
        ScoreEngineTemplate engine = scoreEngineTemplateRepository.findOne(id);
        if(engine==null)
            throw new NotFoundException();
        scoreEngineTemplateRepository.delete(engine);
        return getAllScoreEngineTemplates();
    }

}
