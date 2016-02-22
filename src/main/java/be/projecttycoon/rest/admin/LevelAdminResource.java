package be.projecttycoon.rest.admin;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.LevelRepository;
import be.projecttycoon.db.ScoreEngineRepository;
import be.projecttycoon.db.TeamLevelPrestationRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.ScoreEngine.ScoreEngine;
import be.projecttycoon.model.Team;
import be.projecttycoon.model.TeamLevelPrestation;
import be.projecttycoon.model.level.Level;
import be.projecttycoon.rest.exception.IllegalStateChangeException;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.util.PublicLevel;
import be.projecttycoon.rest.team.LevelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 18/02/16.
 */
@RestController
@RequestMapping("/api/admin/levels")
public class LevelAdminResource extends LevelResource {

    private ScoreEngineRepository scoreEngineRepository;
    private GameRepository gameRepository;

    @Autowired
    public LevelAdminResource(LevelRepository levelRepository, TeamLevelPrestationRepository teamLevelPrestationRepository, ScoreEngineRepository scoreEngineRepository, GameRepository gameRepository) {
        super(levelRepository, teamLevelPrestationRepository);
        this.scoreEngineRepository = scoreEngineRepository;
        this.gameRepository = gameRepository;
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

                    ScoreEngine scoreEngine = null;
                    List<TeamLevelPrestation> teamLevelPrestations = new ArrayList<>();

                    for(Game game : gameRepository.findAll()){
                        for (Level l: game.getLevels()){
                            if(l.getId() == id){
                                scoreEngine = game.getScoreEngine();
                                for (Team t: game.getTeams()){
                                    teamLevelPrestations.addAll(t.getTeamLevelPrestations());
                                }
                                break;
                            }
                        }
                    }

                    scoreEngine.calculateScores(teamLevelPrestations, level.getLevelKnowledgeAreas());
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
