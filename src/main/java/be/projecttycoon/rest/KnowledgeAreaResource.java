package be.projecttycoon.rest;

import be.projecttycoon.db.KnowledgeAreaRepository;
import be.projecttycoon.model.KnowledgeArea;
import be.projecttycoon.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * Created by thomas on 11/02/16.
 */
@RestController
@RequestMapping("/api/knowledgeareas")
public class KnowledgeAreaResource {
    private final KnowledgeAreaRepository knowledgeAreaRepository;

    @Autowired
    public KnowledgeAreaResource(KnowledgeAreaRepository knowledgeAreaRepository){
            this.knowledgeAreaRepository = knowledgeAreaRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<KnowledgeArea> getAllKnowledgeAreas(){
        List<KnowledgeArea> result =  knowledgeAreaRepository.findAll();
        result.sort((ka1, ka2) -> ka1.getElementNumber() - ka2.getElementNumber());
        return result;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public KnowledgeArea getKnowledgeArea(@PathVariable long id){
        KnowledgeArea found =  knowledgeAreaRepository.findOne(id);
        if(found == null)
            throw new NotFoundException();
        return found;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public KnowledgeArea updateKnowledgeArea(@PathVariable long id, @RequestBody KnowledgeArea knowledgeArea){
        getKnowledgeArea(id);
        knowledgeArea.setId(id);
        return knowledgeAreaRepository.save(knowledgeArea);
    }

    @RequestMapping(method = RequestMethod.POST)
    public KnowledgeArea addNewKnowledgeArea(@RequestBody KnowledgeArea knowledgeArea){
        return knowledgeAreaRepository.save(knowledgeArea);
    }

    @RequestMapping(value = "/multiple",method = RequestMethod.POST)
    public List<KnowledgeArea> addNewKnowledgeAreas(@RequestBody List<KnowledgeArea> knowledgeAreas){
        return knowledgeAreaRepository.save(knowledgeAreas);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deteleKnowledgeArea(@PathVariable long id){
        knowledgeAreaRepository.delete(getKnowledgeArea(id));
    }

}
