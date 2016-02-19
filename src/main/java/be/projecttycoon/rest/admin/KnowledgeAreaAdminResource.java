package be.projecttycoon.rest.admin;

import be.projecttycoon.db.KnowledgeAreaRepository;
import be.projecttycoon.model.KnowledgeArea;
import be.projecttycoon.rest.team.KnowledgeAreaResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by thomas on 18/02/16.
 */
@RestController
@RequestMapping("/api/admin/knowledgeareas")
public class KnowledgeAreaAdminResource extends KnowledgeAreaResource {

    @Autowired
    public KnowledgeAreaAdminResource(KnowledgeAreaRepository knowledgeAreaRepository) {
        super(knowledgeAreaRepository);
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
