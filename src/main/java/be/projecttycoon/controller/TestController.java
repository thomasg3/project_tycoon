package be.projecttycoon.controller;

import be.projecttycoon.db.UserRepository;
import be.projecttycoon.model.Team;
import be.projecttycoon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.security.Principal;
import java.util.*;

/**
 * Created by thomas on 08/02/16.
 */
@RestController
public class TestController {

    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("USER"));
        Team team = new Team("jos", "jos");
        team = userRepository.save(team);
        System.out.println(team.getId());
    }

    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @RequestMapping("/isRegisterdTeam/{teamName}")
    public boolean isRegisterdTeam(@PathVariable String teamName) {
        Team team = userRepository.findByUsername(teamName);
        System.out.println(team.toString());
        return team.isRegistered();
    }

    @RequestMapping(value = "/initTeam", method = RequestMethod.POST)
    public void initTeam(@RequestBody UpdateTeam updateTeam){

        Team t = userRepository.findByUsername(updateTeam.oldUsername);
        t.register(updateTeam.newPassword, updateTeam.newUsername, null);
        userRepository.save(t);
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
