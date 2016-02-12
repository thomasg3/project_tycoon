package be.projecttycoon.rest;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.KnowledgeAreaRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.util.GameBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by thomas on 10/02/16.
 */
@RestController
@RequestMapping(value = "/api/games")
public class GameResource {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final KnowledgeAreaRepository knowledgeAreaRepository;

    @Autowired
    public GameResource(GameRepository gameRepository, TeamRepository teamRepository, KnowledgeAreaRepository knowledgeAreaRepository){
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.knowledgeAreaRepository = knowledgeAreaRepository;
        Game game = new Game("A Game",2,0, Collections.emptyList());
        ArrayList<Team> teams= new ArrayList<Team>();
        teams.addAll(game.getTeams());
        teams.get(0).setTeamname("jos");
        teams.get(0).setPassword("jos");
        teams.get(0).setRegistered(false);
        teams.get(1).setTeamname("jef");
        teams.get(1).setPassword("jef");
        teams.get(1).setRegistered(true);


    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Game> getAllGames(){
        return gameRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Game showGame(@PathVariable long id ){
        return gameRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Produces("application/json")
    public Game createGame(@Valid @RequestBody GameBean inputGame){
        Game game = new Game(inputGame.getName(),inputGame.getAmount(), inputGame.getLevels(), knowledgeAreaRepository.findAll());
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
