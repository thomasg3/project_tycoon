package be.projecttycoon.rest.admin;

import be.projecttycoon.db.TeamLevelPrestationRepository;
import be.projecttycoon.model.TeamLevelPrestation;
import be.projecttycoon.rest.team.TeamLevelPrestationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/multiple", method = RequestMethod.POST)
    public List<TeamLevelPrestation> updateMultiplePrestations(@RequestBody List<TeamLevelPrestation> teamLevelPrestations){
        return teamLevelPrestationRepository.save(teamLevelPrestations);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TeamLevelPrestation updatePrestation(@PathVariable long id, @RequestBody TeamLevelPrestation teamLevelPrestation){
        getOneTeamLevelPrestation(id);
        teamLevelPrestation.setId(id);
        return teamLevelPrestationRepository.save(teamLevelPrestation);
    }
}
