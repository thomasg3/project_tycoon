package be.projecttycoon.rest;

import be.projecttycoon.db.InfoRepository;
import be.projecttycoon.model.Info;
import be.projecttycoon.model.InfoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Collection;

/**
 * Created by michael on 18/02/16.
 */

@RestController
@RequestMapping(value = "/api/info")
public class InfoResource {

//todo search game by team and then look what level they are currently playing so you can search the right info
//game isnt finished yet so i cant do this yet

    private final InfoRepository infoRepository;

    @Autowired
    public InfoResource(InfoRepository infoRepository){
        this.infoRepository=infoRepository;
        Info i = new Info(1,"test info", "http://i.imgur.com/wmZppFn.jpg", InfoType.Image);
        Info i2 = new Info(1,"test video","https://www.youtube.com/embed/czezOcHfLS4",InfoType.Video);
        Info i3 = new Info(1,"test document","/documents/changemanagement.pdf",InfoType.Document);
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
    public Info getInfo(@PathVariable long id){
        return infoRepository.findOne(id);
    }



}
