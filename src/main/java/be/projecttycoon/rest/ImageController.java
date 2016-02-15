package be.projecttycoon.rest;

import be.projecttycoon.model.Team;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Produces;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

/**
 * Created by kiwi on 15/02/2016.
 */


@RestController
@RequestMapping(value = "/api/image")
public class ImageController {

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestBody MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("file")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded ";
            } catch (Exception e) {
                return "You failed to upload " + "=> " + e.getMessage();
            }
        } else {
            return "You failed to upload because the file was empty.";
        }
    }
}
