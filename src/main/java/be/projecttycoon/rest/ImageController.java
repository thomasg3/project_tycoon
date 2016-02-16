package be.projecttycoon.rest;

import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import jdk.nashorn.internal.objects.ArrayBufferView;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Produces;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;


/**
 * Created by kiwi on 15/02/2016.
 */


@RestController
@RequestMapping(value = "/api/image")
public class ImageController {

    @Autowired
    TeamRepository teamrep;



    @RequestMapping(value = "/upload/{teamname}", method = RequestMethod.POST, produces = {"image/jpg","image/gif","image/png"})
    public @ResponseBody String handleFileUpload(@PathVariable String teamname, @RequestParam(value = "file") MultipartFile file,Principal user) {
        if (file !=null && !file.isEmpty() && file.getOriginalFilename().contains(".")) {
            String[] filenameAndExtension=file.getOriginalFilename().split("\\.");
            String extension = "."+filenameAndExtension[filenameAndExtension.length-1];

            try {
                byte[] bytes = file.getBytes();

                Team principal=teamrep.findByTeamname(user.getName());
                System.out.println(principal.getTeamname() +" is equal to " + teamname + "?");
                if(teamname.equals(principal.getTeamname()) || principal.isAdmin()) {

                    Team t = teamrep.findByTeamname(teamname);
                    StringBuilder sb = new StringBuilder();
                    sb.append("data:" + file.getContentType() + ";base64,");
                    sb.append(Base64.encodeBase64String(bytes));
                    String imageBytes = sb.toString();

                    t.setTeamImage(imageBytes);
                    teamrep.save(t);


                    return imageBytes;

                    /*String filename=user.getName()+"_"+System.currentTimeMillis()+extension;
                    String path="D:\\";
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("path"+filename));
                    stream.write(bytes);
                    stream.close();
                    return "path"+filename;*/
                }
                else{
                    //todo not allowed...
                    throw new RuntimeException("not allowed Imagecontroller");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }





}
