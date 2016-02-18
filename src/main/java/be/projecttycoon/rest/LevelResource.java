package be.projecttycoon.rest;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.LevelRepository;
import be.projecttycoon.db.TeamLevelPrestationRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Level;
import be.projecttycoon.model.TeamLevelPrestation;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.util.PublicLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 15/02/16.
 */
@RestController
@RequestMapping("/api/levels")
public class LevelResource {

    private final LevelRepository levelRepository;
    private final TeamLevelPrestationRepository teamLevelPrestationRepository;
    private final GameRepository gameRepository;


    @Autowired
    public LevelResource(LevelRepository levelRepository, TeamLevelPrestationRepository teamLevelPrestationRepository, GameRepository gameRepository){
        this.gameRepository = gameRepository;
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

    @RequestMapping(value="/public/{id}", method = RequestMethod.GET)
    public PublicLevel getPublicLevel(@PathVariable long id){
        Level level = levelRepository.findOne(id);
        if(level == null)
            throw new NotFoundException();
        return new PublicLevel(level);
    }

    @RequestMapping(value="/{id}/prestations", method = RequestMethod.GET)
    public List<TeamLevelPrestation> getAllTeamLevelPrestations(@PathVariable long id){
        return teamLevelPrestationRepository.findByLevel(getLevel(id));
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
