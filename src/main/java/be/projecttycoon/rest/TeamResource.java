package be.projecttycoon.rest;

import be.projecttycoon.auth.UserService;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.util.TeamBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.security.Principal;
import java.util.Collection;

/**
 * Created by thomas on 10/02/16.
 */
@RestController
@RequestMapping(value = "/api/teams")
public class TeamResource  {
    private final TeamRepository teamRepository;
    private final UserService userService;

    @Autowired
    public TeamResource(TeamRepository teamRepository, UserService userService){
        this.teamRepository = teamRepository;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Team getTeamById(@PathVariable long id){
        return teamRepository.findOne(id);
    }

    @RequestMapping(value = "/search/{teamname}", method = RequestMethod.GET)
    @Produces("application/json")
    public Team getTeamByTeamname(@PathVariable String teamname){
        return teamRepository.findByTeamname(teamname);
    }

    @RequestMapping(value="/search/{teamname}/registered", method = RequestMethod.GET)
    @Produces("application/json")
    public boolean isRegisteredTeamByTeamname(@PathVariable String teamname){
        return getTeamByTeamname(teamname).isRegistered();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    @Produces("application/json")
    public void updateTeam(Principal currentUser, @PathVariable long id, @RequestBody TeamBean updateTeam){
        Team team = teamRepository.findOne(id);
        String oldName = team.getTeamname();
        team.register(updateTeam.getNewPassword(), updateTeam.getNewUsername(), null);
        teamRepository.save(team);
        if(oldName.equals(currentUser.getName())){
            UserDetails details = userService.loadUserByUsername(updateTeam.getNewUsername());
            Authentication authentication = new UsernamePasswordAuthenticationToken(details.getUsername(), details.getPassword(), details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }


}
