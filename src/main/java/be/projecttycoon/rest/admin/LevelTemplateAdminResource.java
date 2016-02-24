package be.projecttycoon.rest.admin;

import be.projecttycoon.db.LevelTemplateRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.ScoreEngineTemplate.LevelTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.security.Principal;

/**
 * Created by Jeroen on 24-2-2016.
 */
@RestController
@RequestMapping("/api/admin/levelTemplates")
public class LevelTemplateAdminResource {

    private LevelTemplateRepository levelTemplateRepository;

    @Autowired
    public LevelTemplateAdminResource(LevelTemplateRepository levelTemplateRepository) {
        this.levelTemplateRepository = levelTemplateRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public LevelTemplate getLevelTemplate(Principal team, @PathVariable long id ){
        return levelTemplateRepository.findOne(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public LevelTemplate updateLevel(@PathVariable long id, @RequestBody LevelTemplate level){
        LevelTemplate l = levelTemplateRepository.findOne(id);
        l.setName(level.getName());
        return levelTemplateRepository.save(l);
    }
}
