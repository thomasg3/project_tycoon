package be.projecttycoon.rest;

import be.projecttycoon.db.LevelRepository;
import be.projecttycoon.db.TeamLevelPrestationRepository;
import be.projecttycoon.model.level.Level;
import be.projecttycoon.model.TeamLevelPrestation;
import be.projecttycoon.rest.exception.IllegalStateChangeException;
import be.projecttycoon.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by thomas on 15/02/16.
 */
@RestController
@RequestMapping("/api/levels")
public class LevelResource {

    private final LevelRepository levelRepository;
    private final TeamLevelPrestationRepository teamLevelPrestationRepository;

    @Autowired
    public LevelResource(LevelRepository levelRepository, TeamLevelPrestationRepository teamLevelPrestationRepository){
        this.levelRepository = levelRepository;
        this.teamLevelPrestationRepository = teamLevelPrestationRepository;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Level getLevel(@PathVariable long id){
        Level level = levelRepository.findOne(id);
        if(level == null)
            throw new NotFoundException();
        return level;
    }

    @RequestMapping(value="/{id}/prestations", method = RequestMethod.GET)
    public List<TeamLevelPrestation> getAllTeamLevelPrestations(@PathVariable long id){
        return teamLevelPrestationRepository.findByLevel(getLevel(id));
    }

    @RequestMapping(value="/{id}/change/{state}", method = RequestMethod.GET)
    public Level changeLevelState(@PathVariable long id, @PathVariable String state){
        Level level = levelRepository.findOne(id);
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
