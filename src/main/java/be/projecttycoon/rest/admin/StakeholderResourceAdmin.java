package be.projecttycoon.rest.admin;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.StakeholderRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Stakeholder;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.team.StakeholderResource;
import be.projecttycoon.rest.util.UrlBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kiwi on 17/02/2016.
 */
@RestController
@RequestMapping(value = "/api/admin/stakeholders")
public class StakeholderResourceAdmin extends StakeholderResource{


    @Autowired
    public StakeholderResourceAdmin(StakeholderRepository stakeholderRepository, TeamRepository teamRepository, GameRepository gameRepository) {
        super(stakeholderRepository, teamRepository, gameRepository);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<Stakeholder> getAllStakeHolders(Principal user){
        return stakeholderRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public Stakeholder addStakeholder(@RequestBody Stakeholder stakeholder){
        return stakeholderRepository.save(stakeholder);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Stakeholder showStakeholder(Principal user, @PathVariable long id ){
        Stakeholder stakeholder =  stakeholderRepository.findOne(id);
        if(stakeholder == null)
            throw new NotFoundException();
        return stakeholder;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @Consumes("application/json")
    public Stakeholder updateStakeholder(@RequestBody Stakeholder stakeholder){
        showStakeholder(null, stakeholder.getId());
        return stakeholderRepository.save(stakeholder);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void removeStakeholder(@PathVariable long id){
        stakeholderRepository.delete(id);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
    public UrlBean handleFileUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file !=null && !file.isEmpty() && file.getOriginalFilename().contains(".")) {
            String[] filenameAndExtension=file.getOriginalFilename().split("\\.");
            String extension = "."+filenameAndExtension[filenameAndExtension.length-1];
            try {
                byte[] bytes = file.getBytes();
                String filename="stakeholder_"+System.currentTimeMillis()+extension;
                String path="../images/";
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path+filename));
                stream.write(bytes);
                stream.close();
                String outputPath= "/hosted_resources/"+filename;
                return new UrlBean(outputPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value="/from_level/{levelround}", method = RequestMethod.GET)
    public List<Stakeholder> getStakeholdersOfLevel(@PathVariable int levelround){
        if(levelround > 0)
            return stakeholderRepository.findByLevel(levelround);
        if(levelround == 0)
            return stakeholderRepository.findAll().stream()
                    .filter(s -> s.getLevel() < 1)
                    .collect(Collectors.toList());
        throw new NotFoundException();
    }

}
