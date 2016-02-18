package be.projecttycoon.rest.admin;

import be.projecttycoon.db.LevelRepository;
import be.projecttycoon.db.TeamLevelPrestationRepository;
import be.projecttycoon.rest.team.LevelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thomas on 18/02/16.
 */
@RestController
@RequestMapping("/api/admin/levels")
public class LevelAdminResource extends LevelResource {

    @Autowired
    public LevelAdminResource(LevelRepository levelRepository, TeamLevelPrestationRepository teamLevelPrestationRepository) {
        super(levelRepository, teamLevelPrestationRepository);
    }
}
