package be.projecttycoon.rest.admin;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.KnowledgeAreaRepository;
import be.projecttycoon.db.StartupScript;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.rest.team.GameResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thomas on 18/02/16.
 */
@RestController
@RequestMapping("/api/admin/games")
public class GameAdminResource extends GameResource {

    @Autowired
    public GameAdminResource(StartupScript startupScript, GameRepository gameRepository, TeamRepository teamRepository, KnowledgeAreaRepository knowledgeAreaRepository) {
        super(gameRepository, teamRepository, knowledgeAreaRepository);
        startupScript.run();
    }
}
