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
public class EchoController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
