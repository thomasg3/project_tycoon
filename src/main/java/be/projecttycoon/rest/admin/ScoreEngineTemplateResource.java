package be.projecttycoon.rest.admin;

import be.projecttycoon.db.ScoreEngineTemplateRepository;
import be.projecttycoon.model.ScoreEngine.ScoreEngine;
import be.projecttycoon.model.ScoreEngineTemplate.ScoreEngineTemplate;
import be.projecttycoon.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Jeroen on 23-2-2016.
 */

@RestController
@RequestMapping("/api/admin/scoreEngineTemplate")
public class ScoreEngineTemplateResource {
    ScoreEngineTemplateRepository scoreEngineTemplateRepository;

    @Autowired
    public ScoreEngineTemplateResource(ScoreEngineTemplateRepository scoreEngineTemplateRepository) {
        this.scoreEngineTemplateRepository = scoreEngineTemplateRepository;
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

}
