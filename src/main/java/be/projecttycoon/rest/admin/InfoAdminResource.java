package be.projecttycoon.rest.admin;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.InfoRepository;
import be.projecttycoon.db.LevelRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Info;
import be.projecttycoon.model.InfoType;
import be.projecttycoon.model.Team;
import be.projecttycoon.model.level.Level;
import be.projecttycoon.model.level.LevelState;
import be.projecttycoon.model.level.Open;
import be.projecttycoon.rest.team.InfoResource;
import be.projecttycoon.rest.util.UrlBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by michael on 18/02/16.
 */

@RestController
@RequestMapping(value = "/api/admin/info")
public class InfoAdminResource extends InfoResource{

private LevelRepository levelRepository;

    @Autowired
    public InfoAdminResource(InfoRepository infoRepository, GameRepository gameRepository, TeamRepository teamRepository, LevelRepository levelRepository){
        super(infoRepository,gameRepository,teamRepository);
        this.levelRepository=levelRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Info> getAllInfo(){
        return infoRepository.findAll();
    }

    @RequestMapping(value="/level/{level}", method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Info> getInfoFromLevel(@PathVariable long level){
       Level l =levelRepository.findOne(level);
        if(l == null){
            return infoRepository.findAll().stream()
                    .filter(i -> i.getUnlockedAtLevel() == 0)
                    .collect(Collectors.toList());
        }
        else return infoRepository.findAll().stream()
                .filter(i->i.getUnlockedAtLevel()==l.getRound())
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public void saveInfo(@RequestBody Info i){
        infoRepository.save(i);
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @Consumes("application/json")
    public void upload(@RequestBody MultipartFile file) {
        if (file != null && !file.isEmpty() && file.getOriginalFilename().contains(".")) {

            try {
                byte[] bytes = file.getBytes();

                String path = "../documents/";
                String filename = file.getOriginalFilename();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path + filename));
                stream.write(bytes);
                stream.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @RequestMapping(value="/types", method=RequestMethod.GET)
    @Produces("application/json")
    public Map<Integer,InfoType> getTypes(){
        Map<Integer,InfoType> map = new HashMap<>();
        for(int i = 0;i<InfoType.values().length;i++){
            map.put(i,InfoType.values()[i]);
        }
        return map;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Info findOneInfo(Principal user, @PathVariable long id){
        return infoRepository.findOne(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public Collection<Info> deleteInfo(@PathVariable long id){
        Info i = infoRepository.findOne(id);
        deleteFile(i.getPath());
        infoRepository.delete(id);
        return getAllInfo();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateInfo(@PathVariable long id,@RequestBody Info i){
        Info info = infoRepository.findOne(id);
        if(info.getType().equals(InfoType.Document)&&!info.getPath().equals(i.getPath())){
            deleteFile(info.getPath());
        }
        info.setUnlockedAtLevel(i.getUnlockedAtLevel());
        info.setDescription(i.getDescription());
        info.setPath(i.getPath());
        info.setType(i.getType());
        info.setTags(i.getTags());
        infoRepository.save(info);
    }

    @RequestMapping(value="/{id}/team/{team}", method = RequestMethod.DELETE)
    public void removeTeamFromBlackList(@PathVariable long id, @PathVariable long team){
        Info i = infoRepository.findOne(id);
        i.removeTeamFromBlackList(team);
        infoRepository.save(i);
    }

    @RequestMapping(value="/{id}/team/{team}", method = RequestMethod.GET)
    public void addTeamToBlackList(@PathVariable long id, @PathVariable(value="team") long team){
        Info i = infoRepository.findOne(id);
        i.addTeamToBlackList(team);
        infoRepository.save(i);
    }
    private boolean deleteFile(String path){
        File f  = new File(".."+path);
        return f.delete();
    }
}
