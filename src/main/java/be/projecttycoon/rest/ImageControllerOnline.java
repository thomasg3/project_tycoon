package be.projecttycoon.rest;

import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.security.Principal;


/**
 * Created by kiwi on 15/02/2016.
 */


@RestController
@RequestMapping(value = "/api/image")
public class ImageControllerOnline {

    @Autowired
    TeamRepository teamrep;



    @RequestMapping(value = "/uploadWeb/{teamname}", method = RequestMethod.POST)
    public void handleFileUpload(@PathVariable String teamname, @RequestBody String url,Principal user) {
        if (url !=null && !url.isEmpty()) {
            try {

                Team principal=teamrep.findByTeamname(user.getName());
                if(teamname.equals(principal.getTeamname()) || principal.isAdmin()) {
                    Team t = teamrep.findByTeamname(teamname);
                    t.setTeamImage(url);
                    teamrep.save(t);
                }
                else{
                    //todo not allowed...
                    throw new RuntimeException("not allowed Imagecontroller");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }





}
