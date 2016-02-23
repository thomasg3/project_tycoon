package be.projecttycoon.rest.admin;

import be.projecttycoon.auth.UserService;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.team.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.security.Principal;
import java.util.List;

/**
 * Created by thomas on 18/02/16.
 */
@RestController
@RequestMapping("/api/admin/teams")
public class TeamAdminResource extends TeamResource{

    @Autowired
    public TeamAdminResource(TeamRepository teamRepository, UserService userService) {
        super(teamRepository, userService);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Team getTeamById(Principal user, @PathVariable long id){
        Team team = teamRepository.findOne(id);
        if(team == null)
            throw new NotFoundException();
        return team;
    }

    @RequestMapping(value = "/search/{teamname}", method = RequestMethod.GET)
    @Produces("application/json")
    public Team getTeamByTeamname(Principal user, @PathVariable String teamname){
        Team team = teamRepository.findByTeamname(teamname);
        if(team == null)
            throw new NotFoundException();
        return team;
    }




}
