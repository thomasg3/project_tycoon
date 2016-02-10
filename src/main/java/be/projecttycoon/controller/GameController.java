package be.projecttycoon.controller;

import be.projecttycoon.model.Game;
import be.projecttycoon.model.Team;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

/**
 * Created by michael on 09/02/16.
 */
@RestController
public class GameController {

    @RequestMapping("/game/*")
    public Set<Team> showGame(){
        //todo get correct game out DB
        Game g = new Game("Demo Game",3);
        return g.getTeams();
    }
    @RequestMapping("/createGame")
    public Game createGame(@RequestParam(value="gameName") String name,@RequestParam(value="teamAmounts") int amount){
        Game g = new Game(name,amount);
        return g;
    }
}
