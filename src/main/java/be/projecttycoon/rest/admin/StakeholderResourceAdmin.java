package be.projecttycoon.rest.admin;

import be.projecttycoon.db.StakeholderRepository;
import be.projecttycoon.model.Stakeholder;
import be.projecttycoon.rest.team.StakeholderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.security.Principal;

/**
 * Created by kiwi on 17/02/2016.
 */
@RestController
@RequestMapping(value = "/api/admin/stakeholders")
public class StakeholderResourceAdmin extends StakeholderResource{


    @Autowired
    public StakeholderResourceAdmin(StakeholderRepository stakeholderRepository){
        super(stakeholderRepository);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Stakeholder showStakeholder(Principal team, @PathVariable long id ){
        return stakeholderRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public void addStakeholder(Stakeholder stakeholder){
        stakeholderRepository.save(stakeholder);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    @Consumes("application/json")
    public void removeStakeholder(Stakeholder stakeholder){
        stakeholderRepository.delete(stakeholder);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @Consumes("application/json")
    public void updateStakeholder(Stakeholder stakeholder){
        stakeholderRepository.save(stakeholder);
    }


}
