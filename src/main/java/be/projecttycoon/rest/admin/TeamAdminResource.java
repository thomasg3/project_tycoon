package be.projecttycoon.rest.admin;

import be.projecttycoon.auth.UserService;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.team.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.Collection;

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


}
