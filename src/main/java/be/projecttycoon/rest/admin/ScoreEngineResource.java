package be.projecttycoon.rest.admin;

import be.projecttycoon.db.KnowledgeAreaRepository;
import be.projecttycoon.db.ScoreEngineRepository;
import be.projecttycoon.db.ScoreEngineTemplateRepository;
import be.projecttycoon.model.ScoreEngine.ScoreEngine;
import be.projecttycoon.model.ScoreEngine.ScoreFormat;
import be.projecttycoon.model.ScoreEngineTemplate.ScoreEngineTemplate;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.util.ScoreEngineBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 22-2-2016.
 */
@RestController
@RequestMapping("/api/admin/scoreengine")
public class ScoreEngineResource {

    private ScoreEngineRepository scoreEngineRepository;
    private KnowledgeAreaRepository knowledgeAreaRepository;
    private ScoreEngineTemplateRepository scoreEngineTemplateRepository;

    @Autowired
    public ScoreEngineResource(ScoreEngineRepository scoreEngineRepository, KnowledgeAreaRepository knowledgeAreaRepository, ScoreEngineTemplateRepository scoreEngineTemplateRepository){
        this.scoreEngineRepository = scoreEngineRepository;
        this.knowledgeAreaRepository = knowledgeAreaRepository;
        this.scoreEngineTemplateRepository = scoreEngineTemplateRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public ScoreEngine getScoreEngine(@PathVariable long id ){
        ScoreEngine scoreEngine = scoreEngineRepository.findOne(id);
        if(scoreEngine == null)
            throw new NotFoundException();
        return scoreEngine;
    }

    @RequestMapping(value = "/limit", method = RequestMethod.GET)
    @Produces("application/json")
    public List<ScoreEngineBean> getScoreEngineInfo(){
        List<ScoreEngineBean> sebeans = new ArrayList<>();
        List<ScoreEngine> scoreEngines = scoreEngineRepository.findAll();
        for(ScoreEngine se: scoreEngines){
            sebeans.add(new ScoreEngineBean(se.getLevels().size(), se.getName(), se.getId()));
        }
        return sebeans;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<ScoreEngine> getAllScoreEngines(){
        List<ScoreEngine> scoreEngines =  scoreEngineRepository.findAll();
        return scoreEngines;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Produces("application/json")
    public ScoreEngine createScoreEngine(@RequestBody long id){
        ScoreEngineTemplate scoreEngineTemplate = scoreEngineTemplateRepository.findOne(id);
        if(scoreEngineTemplate==null)
            throw new NotFoundException();
        ScoreEngine se = new ScoreEngine(scoreEngineTemplate);
        return scoreEngineRepository.save(se);
    }


    @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
    @Produces("application/json")
    public List<ScoreEngine> deleteScoreEngine(@PathVariable long id){
        ScoreEngine engine = scoreEngineRepository.findOne(id);
        if(engine==null)
            throw new NotFoundException();
        scoreEngineRepository.delete(engine);
        return getAllScoreEngines();
    }

}
