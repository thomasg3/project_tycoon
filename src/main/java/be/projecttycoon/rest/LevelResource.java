package be.projecttycoon.rest;

import be.projecttycoon.db.LevelRepository;
import be.projecttycoon.model.Level;
import be.projecttycoon.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by thomas on 15/02/16.
 */
@RestController
@RequestMapping("/api/levels")
public class LevelResource {

    private final LevelRepository levelRepository;

    @Autowired
    public LevelResource(LevelRepository levelRepository){
        this.levelRepository = levelRepository;
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public Level findOneLevel(@PathVariable long id){
        Level level = levelRepository.findOne(id);
        if(level == null)
            throw new NotFoundException();
        return level;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Level updateLevel(@PathVariable long id, @RequestBody Level level){
        findOneLevel(id);
        level.setId(id);
        return levelRepository.save(level);
    }

}
