package be.projecttycoon.controller;

import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

/**
 * Created by thomas on 08/02/16.
 */
@RestController
public class TestController {

    private TeamRepository teamRepository;

    public TeamRepository getTeamRepository() {
        return teamRepository;
    }
    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("USER"));
        Team team = new Team("jos", "jos");
        team = teamRepository.save(team);

        Team team2 = new Team("jef", "jef");
        team2.setRegistered(true);
        team2 = teamRepository.save(team2);
    }

    /*
    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }*/

    @RequestMapping("/isRegisterdTeam/{teamName}")
    public boolean isRegisterdTeam(@PathVariable String teamName) {
        Team team = teamRepository.findByTeamname(teamName);
        System.out.println(team.toString());
        return team.isRegistered();
    }

    @RequestMapping(value = "/initTeam", method = RequestMethod.POST)
    public void initTeam(@RequestBody UpdateTeam updateTeam){

        Team t = teamRepository.findByTeamname(updateTeam.oldUsername);
        t.register(updateTeam.newPassword, updateTeam.newUsername, null);
        teamRepository.save(t);
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    //Class body voor initTeam requestBody op te vangen, oet static zijn voor POST
    private static class UpdateTeam{
        public String oldUsername;
        public String oldPassword;
        public String newUsername;
        public String newPassword;

        public UpdateTeam(){}

        public UpdateTeam(String oldUsername, String oldPassword, String newUsername, String newPassword){
            this.oldUsername = oldUsername;
            this.oldPassword = oldPassword;
            this.newUsername = newUsername;
            this.newPassword = newPassword;
        }
    }
}
