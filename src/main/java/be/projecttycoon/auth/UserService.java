package be.projecttycoon.auth;

import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 09/02/16.
 */
@Component
public class UserService implements UserDetailsService {

    private final TeamRepository teamRepository;

    @Autowired
    public UserService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Team team =  teamRepository.findByTeamname(s);
        if(team != null){
            List<GrantedAuthority> auths = new ArrayList<>();
            auths.add(SecurityAuths.UNREGISTERED.getGrantedAuthority());
            if(team.isRegistered()){
                auths.add(SecurityAuths.TEAM.getGrantedAuthority());
            }
            if(team.isAdmin()){
                auths.add(SecurityAuths.ADMIN.getGrantedAuthority());
            }
            return new org.springframework.security.core.userdetails.User(team.getTeamname(), team.getPassword(), auths);
        }
        return null;
    }
}
