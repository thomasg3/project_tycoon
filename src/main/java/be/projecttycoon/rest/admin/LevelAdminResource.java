package be.projecttycoon.rest.admin;

import be.projecttycoon.db.LevelRepository;
import be.projecttycoon.db.TeamLevelPrestationRepository;
import be.projecttycoon.model.level.Level;
import be.projecttycoon.rest.exception.IllegalStateChangeException;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.team.LevelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by thomas on 18/02/16.
 */
@RestController
@RequestMapping("/api/admin/levels")
public class LevelAdminResource extends LevelResource {

    @Autowired
    public LevelAdminResource(LevelRepository levelRepository, TeamLevelPrestationRepository teamLevelPrestationRepository) {
        super(levelRepository, teamLevelPrestationRepository);
    }

    @RequestMapping(value="/{id}/change/{state}", method = RequestMethod.GET)
    public Level changeLevelState(@PathVariable long id, @PathVariable String state){
        Level level = getLevel(id);
        try {
            state = state.toLowerCase();
            switch (state){
                case "open":
                    level.openUp();
                    break;
                case "close":
                    level.closeUp();
                    break;
                case "push":
                    level.pointPush();
                    break;
                case "conclude":
                    level.cermonieFinished();
                    break;
                default:
                    throw new NotFoundException("no such state transistion");
            }
        } catch (IllegalStateException e){
            throw new IllegalStateChangeException(e);
        }
        return levelRepository.save(level);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Level updateLevel(@PathVariable long id, @RequestBody Level level){
        getLevel(id);
        level.setId(id);
        return levelRepository.save(level);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Level addLevel(@RequestBody Level level){
        level = levelRepository.save(level);
        return level;
    }
}
