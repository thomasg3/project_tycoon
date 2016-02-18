package be.projecttycoon.rest.team;

import be.projecttycoon.db.TeamLevelPrestationRepository;
import be.projecttycoon.model.TeamLevelPrestation;
import be.projecttycoon.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by thomas on 15/02/16.
 */
@RestController
@RequestMapping("/api/prestations")
public class TeamLevelPrestationResource {
    protected final TeamLevelPrestationRepository teamLevelPrestationRepository;

    @Autowired
    public TeamLevelPrestationResource(TeamLevelPrestationRepository teamLevelPrestationRepository){
        this.teamLevelPrestationRepository = teamLevelPrestationRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TeamLevelPrestation getOneTeamLevelPrestation(@PathVariable long id){
        TeamLevelPrestation teamLevelPrestation = teamLevelPrestationRepository.findOne(id);
        if(teamLevelPrestation==null)
            throw new NotFoundException();
        return teamLevelPrestation;
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
