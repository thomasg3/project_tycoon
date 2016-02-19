package be.projecttycoon.rest.team;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.KnowledgeAreaRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.*;
import be.projecttycoon.model.level.*;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.util.GameBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.*;

/**
 * Created by thomas on 10/02/16.
 */
@RestController
@RequestMapping(value = "/api/games")
public class GameResource {

    protected final GameRepository gameRepository;
    protected final TeamRepository teamRepository;
    protected final KnowledgeAreaRepository knowledgeAreaRepository;

    @Autowired
    public GameResource(GameRepository gameRepository, TeamRepository teamRepository, KnowledgeAreaRepository knowledgeAreaRepository){
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.knowledgeAreaRepository = knowledgeAreaRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Game showGame(@PathVariable long id ){
        Game game = gameRepository.findOne(id);
        if(game == null)
            throw new NotFoundException();
        return game;
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






}
