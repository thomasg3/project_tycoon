package be.projecttycoon.auth;

import be.projecttycoon.db.UserRepository;
import be.projecttycoon.model.Team;
import be.projecttycoon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * Created by thomas on 09/02/16.
 */
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Team team =  userRepository.findByUsername(s);
        if(team != null){
            return new org.springframework.security.core.userdetails.User(team.getUsername(), team.getPassword(), new ArrayList<>());
        }
        return null;
    }
}
