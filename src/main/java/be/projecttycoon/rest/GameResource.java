package be.projecttycoon.rest;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.util.GameBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by thomas on 10/02/16.
 */
@RestController
@RequestMapping(value = "/api/games")
public class GameResource {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public GameResource(GameRepository gameRepository, TeamRepository teamRepository){
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Game> getAllGames(){
        return gameRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Game showGame(@PathVariable long id ){
        return gameRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Produces("application/json")
    public Game createGame(@RequestBody GameBean inputGame){
        Game game = new Game(inputGame.getGameName(),inputGame.getAmount());
        game = gameRepository.save(game);
        return game;
    }

    @RequestMapping(value="/game/{teamname}" ,method = RequestMethod.GET)
    @Produces("application/json")
    public Game getGameForTeam(@PathVariable String teamname) {
        Team team = teamRepository.findByTeamname(teamname);
        return gameRepository.findAll().stream()
                .filter(g -> g.containsTeam(team))
                .findFirst().get();
    }
}
