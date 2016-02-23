package be.projecttycoon.rest.team;

import be.projecttycoon.auth.UserService;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.util.TeamBean;
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
import java.util.Map;

/**
 * Created by thomas on 10/02/16.
 */
@RestController
@RequestMapping(value = "/api/teams")
public class TeamResource  {
    protected final TeamRepository teamRepository;
    protected final UserService userService;

    @Autowired
    public TeamResource(TeamRepository teamRepository, UserService userService){
        this.teamRepository = teamRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Team getTeamById(Principal user, @PathVariable long id){
        Team team = teamRepository.findOne(id);
        if(team == null)
            throw new NotFoundException();
        return sanitizeTeam(user, team);
    }

    @RequestMapping(value = "/search/{teamname}", method = RequestMethod.GET)
    @Produces("application/json")
    public Team getTeamByTeamname(Principal user, @PathVariable String teamname){
        Team team = teamRepository.findByTeamname(teamname);
        if(team == null)
            throw new NotFoundException();
        return sanitizeTeam(user, team);
    }

    @RequestMapping(value="/search/{teamname}/registered", method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String, Boolean> isRegisteredTeamByTeamname(Principal user, @PathVariable String teamname){
        return Collections.singletonMap("registered", getTeamByTeamname(user, teamname).isRegistered());
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    @Produces("application/json")
    public void updateTeam(Principal currentUser, @PathVariable long id, @Valid @RequestBody TeamBean updateTeam){
        Team team = teamRepository.findOne(id);
        String oldName = team.getTeamname();
        team.register(updateTeam.getPassword(), updateTeam.getTeamname(), updateTeam.getEmail());
        teamRepository.save(team);
        if(oldName.equals(currentUser.getName())){
            UserDetails details = userService.loadUserByUsername(updateTeam.getTeamname());
            Authentication authentication = new UsernamePasswordAuthenticationToken(details.getUsername(), details.getPassword(), details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private Team sanitizeTeam(Principal user, Team team){
        if(team.getTeamname().equals(user.getName())){
            team.setTeamLevelPrestations(team.getOwnTeamLevelPrestations());
        } else {
            team.setTeamLevelPrestations(team.getPublicTeamLevelPrestations());
        }
        return team;
    }
}
