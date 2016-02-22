package be.projecttycoon.rest.team;

import be.projecttycoon.db.StakeholderRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Stakeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.security.Principal;
import java.util.List;

/**
 * Created by kiwi on 17/02/2016.
 */
@RestController
@RequestMapping(value = "/api/stakeholders")
public class StakeholderResource {
    protected final StakeholderRepository stakeholderRepository;

    @Autowired
    public StakeholderResource(StakeholderRepository stakeholderRepository){
        this.stakeholderRepository = stakeholderRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public List<Stakeholder> getAllStakeHolders(){
        return this.stakeholderRepository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Stakeholder showStakeholder(Principal team, @PathVariable long id ){
        //todo check if team is allowed to see this stakeholder...
        return stakeholderRepository.findOne(id);
    }


}
