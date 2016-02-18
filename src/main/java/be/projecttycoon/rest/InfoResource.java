package be.projecttycoon.rest;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.InfoRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Info;
import be.projecttycoon.model.InfoType;
import be.projecttycoon.model.level.Level;
import be.projecttycoon.model.level.LevelState;
import be.projecttycoon.model.level.Open;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by michael on 18/02/16.
 */

@RestController
@RequestMapping(value = "/api/info")
public class InfoResource {

//todo search game by team and then look what level they are currently playing so you can search the right info
//game isnt finished yet so i cant do this yet

    private final InfoRepository infoRepository;
    private final GameRepository gameRepository;

    @Autowired
    public InfoResource(InfoRepository infoRepository, GameRepository gameRepository){
        this.infoRepository=infoRepository;
        this.gameRepository=gameRepository;
        Info i = new Info(1,"test info", "http://i.imgur.com/1rHMtFM.gif", InfoType.Image);
        Info i2 = new Info(1,"test video","https://www.youtube.com/embed/czezOcHfLS4",InfoType.Video);
        Info i3 = new Info(2,"test document","/documents/changemanagement.pdf",InfoType.Document);
        infoRepository.save(i);
        infoRepository.save(i2);
        infoRepository.save(i3);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Info> getAllInfo(){
        return infoRepository.findAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Info> getInfo(@PathVariable long id){
        Game g = gameRepository.findOne(id);
       int round = g.getLevels().stream()
                .filter(l->l.getState().equals("Open"))
                .mapToInt(Level::getRound)
                .max().orElse(-1);
        Collection<Info> info = infoRepository.findAll().stream()
                .filter(i->i.getUnlockedAtLevel() <= round)
                .collect(Collectors.toList());
        return info;
    }



}
