package be.projecttycoon.rest;

import be.projecttycoon.db.KnowledgeAreaRepository;
import be.projecttycoon.model.KnowledgeArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
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
        String[] areas = {"Integration", "Scope", "Time", "Cost", "Quality", "Human Resources", "Communications", "Risk", "Procurement", "Stakeholder"};
        for(int i=0; i<areas.length; i++){
            knowledgeAreaRepository.save(new KnowledgeArea(areas[i], i));
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<KnowledgeArea> getAllKnowledgeAreas(){
        List<KnowledgeArea> result =  knowledgeAreaRepository.findAll();
        result.sort((ka1, ka2) -> ka1.getElementNumber() - ka2.getElementNumber());
        return result;
    }
}
