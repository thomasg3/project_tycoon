package be.projecttycoon.rest.admin;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.InfoRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Info;
import be.projecttycoon.model.InfoType;
import be.projecttycoon.model.Team;
import be.projecttycoon.model.level.Level;
import be.projecttycoon.model.level.LevelState;
import be.projecttycoon.model.level.Open;
import be.projecttycoon.rest.util.UrlBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by michael on 18/02/16.
 */

@RestController
@RequestMapping(value = "/api/admin/info")
public class InfoAdminResource {

//game isnt finished yet so i cant do this yet

    private final InfoRepository infoRepository;
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public InfoAdminResource(InfoRepository infoRepository, GameRepository gameRepository, TeamRepository teamRepository){
        this.infoRepository=infoRepository;
        this.gameRepository=gameRepository;
        this.teamRepository=teamRepository;

    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Info> getAllInfo(){
        return infoRepository.findAll();
    }

    @RequestMapping(value="/team/{id}",method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Info> getInfo(@PathVariable long id){
        Team t = teamRepository.findOne(id);
        Game game = gameRepository.findAll().stream()
                .filter(g->g.getTeams().contains(t))
                .findFirst().orElse(null);
        int round = game.getLevels().stream()
                .filter(l->l.documentsAreOpen())
                .mapToInt(Level::getRound)
                .max().orElse(-1);
        Collection<Info> info = infoRepository.findAll().stream()
                .filter(i->i.getUnlockedAtLevel() <= round)
                .collect(Collectors.toList());
        return info;
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
    public Info getInfoById(@PathVariable long id){
        return infoRepository.findOne(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public Collection<Info> deleteInfo(@PathVariable long id){
        infoRepository.delete(id);
        return getAllInfo();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateInfo(@PathVariable long id,@RequestBody Info i){
        Info info = infoRepository.findOne(id);
        info.setUnlockedAtLevel(i.getUnlockedAtLevel());
        info.setDescription(i.getDescription());
        info.setPath(i.getPath());
        info.setType(i.getType());
        infoRepository.save(info);
    }
}
