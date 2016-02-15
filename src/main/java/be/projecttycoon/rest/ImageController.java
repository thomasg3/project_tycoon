package be.projecttycoon.rest;

import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Produces;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.Collection;

/**
 * Created by kiwi on 15/02/2016.
 */


@RestController
@RequestMapping(value = "/api/image")
public class ImageController {

    @Autowired
    TeamRepository teamrep;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(@RequestParam(value = "file") MultipartFile file,Principal user) {
        if (file !=null && !file.isEmpty() && file.getOriginalFilename().contains(".")) {
            String[] filenameAndExtension=file.getOriginalFilename().split("\\.");
            String extension = "."+filenameAndExtension[filenameAndExtension.length-1];

            try {
                byte[] bytes = file.getBytes();
                String filename=user.getName()+"_"+System.currentTimeMillis()+extension;
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("src/main/resources/static/img/profiles/"+filename));
                stream.write(bytes);

                return "img/profiles/"+filename;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }





}
