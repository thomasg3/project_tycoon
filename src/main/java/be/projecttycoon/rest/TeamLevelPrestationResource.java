package be.projecttycoon.rest;

import be.projecttycoon.db.TeamLevelPrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thomas on 15/02/16.
 */
@RestController
@RequestMapping("/api/prestations")
public class TeamLevelPrestationResource {
    private final TeamLevelPrestationRepository teamLevelPrestationRepository;

    @Autowired
    public TeamLevelPrestationResource(TeamLevelPrestationRepository teamLevelPrestationRepository){
        this.teamLevelPrestationRepository = teamLevelPrestationRepository;
    }




}
