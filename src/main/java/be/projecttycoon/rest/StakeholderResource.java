package be.projecttycoon.rest;

import be.projecttycoon.db.StakeholderRepository;
import be.projecttycoon.model.Stakeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kiwi on 17/02/2016.
 */
@RestController
@RequestMapping(value = "/api/stakeholders")
public class StakeholderResource {
    private final StakeholderRepository stakeholderRepository;

    @Autowired
    public StakeholderResource(StakeholderRepository stakeholderRepository){
        this.stakeholderRepository = stakeholderRepository;
    }

    public List<Stakeholder> getAllSt

}
