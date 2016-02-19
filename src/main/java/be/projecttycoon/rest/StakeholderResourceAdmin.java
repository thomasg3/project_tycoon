package be.projecttycoon.rest;

import be.projecttycoon.db.StakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kiwi on 17/02/2016.
 */
@RestController
@RequestMapping(value = "/api/admin/stakeholders")
public class StakeholderResourceAdmin {
    private final StakeholderRepository stakeholderRepository;

    @Autowired
    public StakeholderResourceAdmin(StakeholderRepository stakeholderRepository){
        this.stakeholderRepository = stakeholderRepository;
    }

}
