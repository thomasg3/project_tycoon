package be.projecttycoon.rest.team;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.StakeholderRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Stakeholder;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kiwi on 17/02/2016.
 */
@RestController
@RequestMapping(value = "/api/stakeholders")
public class StakeholderResource {
    protected final StakeholderRepository stakeholderRepository;
    protected final TeamRepository teamRepository;
    protected final GameRepository gameRepository;


    @Autowired
    public StakeholderResource(StakeholderRepository stakeholderRepository, TeamRepository teamRepository, GameRepository gameRepository){
        this.stakeholderRepository = stakeholderRepository;
        this.teamRepository = teamRepository;
        this.gameRepository = gameRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public List<Stakeholder> getAllStakeHolders(Principal user){
        int threshold  = findThresholdForUser(user);
        return this.stakeholderRepository.findAll().stream()
                .map(s -> {
                    if(s.getLevel() <= threshold)
                        return s;
                    else
                        return s.anonymous();
                }).collect(Collectors.toList());
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Stakeholder showStakeholder(Principal user, @PathVariable long id ){
        int threshold = findThresholdForUser(user);
        Stakeholder stakeholder = stakeholderRepository.findOne(id);
        if(stakeholder.getLevel() <= threshold)
            return stakeholder;
        else return stakeholder.anonymous();
    }



    private int findThresholdForUser(Principal user){
        Team team = teamRepository.findByTeamname(user.getName());
        if(team == null)
            throw new NotFoundException();
        Game game = gameRepository.findAll().stream()
                .filter(g -> g.containsTeam(team))
                .findFirst().orElse(null);
        if(game == null)
            throw new NotFoundException();
        return game.openLevel();
    }


}
