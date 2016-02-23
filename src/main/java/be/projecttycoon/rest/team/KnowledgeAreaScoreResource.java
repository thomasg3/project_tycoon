package be.projecttycoon.rest.team;

import be.projecttycoon.db.KnowledgeAreaScoreRepository;
import be.projecttycoon.model.KnowledgeAreaScore;
import be.projecttycoon.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jeroen on 18-2-2016.
 */
@RestController
@RequestMapping("/api/knowledgeareascores")
public class KnowledgeAreaScoreResource {

    private final KnowledgeAreaScoreRepository knowledgeAreaScoreRepository;

    @Autowired
    public KnowledgeAreaScoreResource(KnowledgeAreaScoreRepository knowledgeAreaScoreRepository){
        this.knowledgeAreaScoreRepository = knowledgeAreaScoreRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<KnowledgeAreaScore> getAllKnowledgeAreaScores(){
        List<KnowledgeAreaScore> result =  knowledgeAreaScoreRepository.findAll();
        result.forEach(kas -> kas.setScore(0));
        return result;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public KnowledgeAreaScore getKnowledgeAreaScore(@PathVariable long id){
        KnowledgeAreaScore found =  knowledgeAreaScoreRepository.findOne(id);
        if(found == null)
            throw new NotFoundException();
        found.setScore(0);
        return found;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public KnowledgeAreaScore updateKnowledgeAreaScore(@PathVariable long id, @RequestBody KnowledgeAreaScore knowledgeAreaScore){
        KnowledgeAreaScore knas = getKnowledgeAreaScore(id);
        knas.setAnswer(knowledgeAreaScore.getAnswer());
        return knowledgeAreaScoreRepository.save(knas);
    }
}
