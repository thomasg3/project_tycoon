package be.projecttycoon.rest;

import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.util.TeamBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by thomas on 10/02/16.
 */
@RestController
@RequestMapping(value = "/api/teams")
public class TeamResource  {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamResource(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Team getTeamById(@PathVariable long id){
        return teamRepository.findOne(id);
    }

    @RequestMapping(value = "/search/{teamname}", method = RequestMethod.GET)
    public Team getTeamByTeamname(@PathVariable String teamname){
        return teamRepository.findByTeamname(teamname);
    }

    @RequestMapping(value="/search/{teamname}/registered", method = RequestMethod.GET)
    public boolean isRegisteredTeamByTeamname(@PathVariable String teamname){
        return getTeamByTeamname(teamname).isRegistered();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public void updateTeam(@PathVariable long id, @RequestBody TeamBean updateTeam){
        Team team = teamRepository.findOne(id);
        team.register(updateTeam.getNewPassword(), updateTeam.getNewUsername(), null);
        teamRepository.save(team);
    }


}
