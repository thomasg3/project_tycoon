package be.projecttycoon.rest.admin;

import be.projecttycoon.db.*;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.KnowledgeAreaScore;
import be.projecttycoon.model.ScoreEngine.ScoreEngine;
import be.projecttycoon.model.ScoreEngineTemplate.ScoreEngineTemplate;
import be.projecttycoon.model.Team;
import be.projecttycoon.model.TeamLevelPrestation;
import be.projecttycoon.model.level.Level;
import be.projecttycoon.rest.exception.NotAuthorizedException;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.team.GameResource;
import be.projecttycoon.rest.util.GameBean;
import be.projecttycoon.rest.util.ShortGameBean;
import be.projecttycoon.rest.util.TeamBeanLight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by thomas on 18/02/16.
 */
@RestController
@RequestMapping("/api/admin/games")
public class GameAdminResource extends GameResource {

    ScoreEngineRepository scoreEngineRepository;
    private ScoreEngineTemplateRepository scoreEngineTemplateRepository;

    @Autowired
    public GameAdminResource(StartupScript startupScript, GameRepository gameRepository, TeamRepository teamRepository, KnowledgeAreaRepository knowledgeAreaRepository, ScoreEngineRepository scoreEngineRepository, ScoreEngineTemplateRepository scoreEngineTemplateRepository) {
        super(gameRepository, teamRepository, knowledgeAreaRepository);
        this.scoreEngineRepository = scoreEngineRepository;
        this.scoreEngineTemplateRepository =scoreEngineTemplateRepository;
        startupScript.run();
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<ShortGameBean> getAllGames(){
        return gameRepository.findAll().stream()
                .map(g -> new ShortGameBean(g.getId(), g.getName()))
                .collect(Collectors.toList());
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Game showGame(Principal team, @PathVariable long id ){
        return getGame(id);
    }

    @Override
    @RequestMapping(value="/game/{teamname}" ,method = RequestMethod.GET)
    @Produces("application/json")
    public Game getGameForTeam(Principal team, @PathVariable String teamname) {
        return getGameByTeamname(teamname);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Produces("application/json")
    public Game createGame(@Valid @RequestBody GameBean inputGame){
        ScoreEngineTemplate scoreEngineTemplate = scoreEngineTemplateRepository.findOne((long)inputGame.getScoreengineid());
        System.out.println(inputGame.toString());
        ScoreEngine scoreEngine = new ScoreEngine(scoreEngineTemplate);
        scoreEngineRepository.save(scoreEngine);

        Game game = new Game(inputGame.getName(),inputGame.getAmount(), scoreEngineRepository.findOne(scoreEngine.getId()));
        return gameRepository.save(game);
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
        getGame(id);
        gameRepository.delete(id);
    }

    @RequestMapping(value = "/team/{id}", method=RequestMethod.DELETE)
    @Produces("application/json")
    public Game deleteTeam(@PathVariable long id){
        Team t = teamRepository.findOne(id);
        if(t==null)
            throw new NotFoundException();
        Game g = getGameByTeamname(t.getTeamname());
        Set<Team> teams= g.getTeams();
        teams.remove(t);
        return gameRepository.save(g);
    }

    @RequestMapping(value = "/{id}/teams", method=RequestMethod.GET)
    @Produces("application/json")
    public List<TeamBeanLight> getAllTeamsForGameLight(@PathVariable long id){
        Game game = gameRepository.findOne(id);
        List<TeamBeanLight> teams = new ArrayList<>();
        for(Team t: game.getTeams()){
            teams.add(new TeamBeanLight(t.getId(), t.getTeamname(), getNumberOfAnsers(t)));
        }
        return teams;
    }

    private int getNumberOfAnsers(Team team){
        int count = 0;
        for(TeamLevelPrestation tlp : team.getTeamLevelPrestations()){
            for(KnowledgeAreaScore kas: tlp.getKnowledgeAreaScores()){
                if(kas.getAnswer() != null){
                    count += 1;
                }
            }
        }
        return count;
    }

    @RequestMapping(value = "/{id}/recalculate", method = RequestMethod.GET)
    @Produces("application/json")
    public Game recalculateGame(@PathVariable long id ){
        Game game = gameRepository.findOne(id);
        ScoreEngine scoreEngine = game.getScoreEngine();
        if(game == null || scoreEngine == null)
            throw new NotFoundException();
        scoreEngine.calculateScoresForCompleteGame(game);
        return gameRepository.save(game);
    }


    @RequestMapping(value = "/{id}/recalculate/{levelid}", method = RequestMethod.GET)
    @Produces("application/json")
    public Game recalculateLevel(@PathVariable long id, @PathVariable long levelid ){
        Game game = gameRepository.findOne(id);
        Level l = null;
        for(Level level : game.getLevels()){
            if(level.getId() == levelid){
                l = level;
            }
        }
        game.getScoreEngine().calculateScores(game.getAllTeamLevelPrestations(), l);
        return gameRepository.save(game);
    }

    private static String teamDelimiter = "\n\n/////////////////////////////////////////////////\n\n";
    @RequestMapping(value="/{id}/teams.txt")
    public String getTeamsInText(@PathVariable long id){
        Game game = getGame(id);
        if(game.getTeams().stream().anyMatch(t -> t.isRegistered())){
            return "There is one registered team, so default passwords are not an option anymore.";
        }
        StringBuilder stringBuilder = new StringBuilder();
        game.getTeams().forEach(t -> {
            stringBuilder.append(teamDelimiter);
            stringBuilder.append("Login Name:   ");
            stringBuilder.append(t.getTeamname());
            stringBuilder.append("\n");
            stringBuilder.append("Password:   ");
            stringBuilder.append(Game.PASSWORD);
            stringBuilder.append("\n");
        });
        return stringBuilder.toString();
    }


}
