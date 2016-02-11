package be.projecttycoon.controller;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

/**
 * Created by michael on 09/02/16.
 */
@RestController
public class GameController {
    //TODO errorhandling
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TeamRepository teamRepository;
    @RequestMapping("/game/{id}")
    public Game showGame(@PathVariable long id ){
       // Game g2 = new Game("Demo Game",3);
        Game g=gameRepository.findById(id);
        return g;
    }

    @RequestMapping("/createGame")
    public Game createGame(@RequestParam(value="gameName") String name,@RequestParam(value="teamAmounts") int amount){
        Game g = new Game(name,amount);
        g=gameRepository.save(g);
        return g;
    }
}
