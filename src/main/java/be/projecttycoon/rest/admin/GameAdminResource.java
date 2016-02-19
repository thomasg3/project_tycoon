package be.projecttycoon.rest.admin;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.KnowledgeAreaRepository;
import be.projecttycoon.db.StartupScript;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.team.GameResource;
import be.projecttycoon.rest.util.GameBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.Set;

/**
 * Created by thomas on 18/02/16.
 */
@RestController
@RequestMapping("/api/admin/games")
public class GameAdminResource extends GameResource {

    @Autowired
    public GameAdminResource(StartupScript startupScript, GameRepository gameRepository, TeamRepository teamRepository, KnowledgeAreaRepository knowledgeAreaRepository) {
        super(gameRepository, teamRepository, knowledgeAreaRepository);
        startupScript.run();
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Game> getAllGames(){
        return gameRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @Produces("application/json")
    public Game createGame(@Valid @RequestBody GameBean inputGame){
        Game game = new Game(inputGame.getName(),inputGame.getAmount(), inputGame.getLevels(), knowledgeAreaRepository.findAll());
        game = gameRepository.save(game);
        return game;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    @Produces("application/json")
    public Game updateGame(@PathVariable long id, @Valid @RequestBody Game newGame){
        Game game = gameRepository.findOne(newGame.getId());
        newGame.setTeams(game.getTeams());
        return gameRepository.save(game);
    }

    @RequestMapping (value="/{id}", method = RequestMethod.DELETE)
    @Produces("application/json")
    public void deleteGame(@PathVariable long id){
        showGame(id);
        gameRepository.delete(id);

    }

    @RequestMapping(value = "/team/{id}", method=RequestMethod.DELETE)
    @Produces("application/json")
    public Game deleteTeam(@PathVariable long id){
        Team t = teamRepository.findOne(id);
        if(t==null)
            throw new NotFoundException();
        Game g = getGameForTeam(t.getTeamname());
        Set<Team> teams= g.getTeams();
        teams.remove(t);
        return gameRepository.save(g);
    }
}
