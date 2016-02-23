package be.projecttycoon.rest.team;

import be.projecttycoon.db.LevelRepository;
import be.projecttycoon.db.TeamLevelPrestationRepository;
import be.projecttycoon.model.level.Level;
import be.projecttycoon.model.TeamLevelPrestation;
import be.projecttycoon.rest.exception.IllegalStateChangeException;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.util.PublicLevel;
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

    protected final LevelRepository levelRepository;
    protected final TeamLevelPrestationRepository teamLevelPrestationRepository;

    @Autowired
    public LevelResource(LevelRepository levelRepository, TeamLevelPrestationRepository teamLevelPrestationRepository){
        this.levelRepository = levelRepository;
        this.teamLevelPrestationRepository = teamLevelPrestationRepository;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Level getLevel(@PathVariable long id){
        Level level = levelRepository.findOne(id);
        level.setLevelKnowledgeAreas(level.getPublicKnowledgeAreas());
        if(level == null)
            throw new NotFoundException();
        return level;
    }

    @RequestMapping(value="/{id}/prestations", method = RequestMethod.GET)
    public List<TeamLevelPrestation> getAllTeamLevelPrestations(@PathVariable long id){
        Level level = getLevel(id);
        List<TeamLevelPrestation> result =  teamLevelPrestationRepository.findByLevel(level);

        result.forEach(tlp -> {});

        return result;
    }

    @RequestMapping(value="/public/{id}", method = RequestMethod.GET)
    public PublicLevel getPublicLevel(@PathVariable long id){
        Level level = levelRepository.findOne(id);
        if(level == null)
            throw new NotFoundException();
        return new PublicLevel(level);
    }


}
