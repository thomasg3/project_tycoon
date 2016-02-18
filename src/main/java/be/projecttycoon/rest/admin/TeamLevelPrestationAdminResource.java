package be.projecttycoon.rest.admin;

import be.projecttycoon.db.TeamLevelPrestationRepository;
import be.projecttycoon.rest.team.TeamLevelPrestationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thomas on 18/02/16.
 */
@RestController
@RequestMapping("/api/admin/prestations")
public class TeamLevelPrestationAdminResource extends TeamLevelPrestationResource {

    @Autowired
    public TeamLevelPrestationAdminResource(TeamLevelPrestationRepository teamLevelPrestationRepository) {
        super(teamLevelPrestationRepository);
    }
}
