package be.projecttycoon.rest;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.KnowledgeAreaRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.KnowledgeArea;
import be.projecttycoon.model.ScoreEngine;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.util.GameBean;
import be.projecttycoon.rest.KnowledgeAreaResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

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

        String[] areas = {"Integration", "Scope", "Time", "Cost", "Quality", "Human Resources", "Communications", "Risk", "Procurement", "Stakeholder"};
        for(int i=0; i<areas.length; i++){
            knowledgeAreaRepository.save(new KnowledgeArea(areas[i], i));
        }

        Game game = new Game("ProjectFun2016",2,4, knowledgeAreaRepository.findAll());
        ArrayList<Team> teams= new ArrayList<Team>();
        teams.addAll(game.getTeams());
        teams.get(0).setTeamname("joskes");
        teams.get(0).setPassword("joskes");
        teams.get(0).setRegistered(false);
        teams.get(1).setTeamname("jefkes");
        teams.get(1).setPassword("jefkes");
        teams.get(1).setRegistered(true);

        Game testgame = new Game("testGame123", 5,5, knowledgeAreaRepository.findAll());
        ArrayList<Team> teams2= new ArrayList<Team>();
        teams2.addAll(testgame.getTeams());
        teams2.get(0).setTeamname("Team123");
        teams2.get(0).setPassword("azerty");


        Game testgame2 = new Game("testGame123342", 5,5, knowledgeAreaRepository.findAll());
        ArrayList<Team> teams3= new ArrayList<Team>();
        teams2.addAll(testgame.getTeams());
        teams2.get(0).setTeamname("Team123");
        teams2.get(0).setPassword("azerty");
        teams2.get(0).setRegistered(true);

        gameRepository.save(testgame);
        gameRepository.save(testgame2);
        gameRepository.save(game);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Game> getAllGames(){
        return gameRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Game showGame(@PathVariable long id ){
        Game game = gameRepository.findOne(id);
        if(game == null)
            throw new NotFoundException();
        return game;
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
        return gameRepository.save(newGame);
    }

    @RequestMapping(value="/game/{teamname}" ,method = RequestMethod.GET)
    @Produces("application/json")
    public Game getGameForTeam(@PathVariable String teamname) {
        Team team = teamRepository.findByTeamname(teamname);
        Game game =  gameRepository.findAll().stream()
                .filter(g -> g.containsTeam(team))
                .findFirst().orElse(null);
        if(game == null)
            throw new NotFoundException();
        return game;
    }

    @RequestMapping (value="/{id}", method = RequestMethod.DELETE)
    @Produces("application/json")
    public void deleteGame(@PathVariable long id){
        showGame(id);
        gameRepository.delete(id);

    }

    @RequestMapping(value = "/team/{id}", method=RequestMethod.DELETE)
    @Produces("application/json")
    public void deleteTeam(@PathVariable long id){
        Team t = teamRepository.findOne(id);
        if(t==null)
            throw new NotFoundException();
        Game g = getGameForTeam(t.getTeamname());
        Set<Team> teams= g.getTeams();
        teams.remove(t);
        gameRepository.save(g);
    }
}
