package be.projecttycoon.auth;

import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * Created by thomas on 09/02/16.
 */
public class UserService implements UserDetailsService {

    private final TeamRepository teamRepository;

    public UserService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Team team =  teamRepository.findByTeamname(s);
        if(team != null){
            return new org.springframework.security.core.userdetails.User(team.getTeamname(), team.getPassword(), new ArrayList<>());
        }
        return null;
    }
}
