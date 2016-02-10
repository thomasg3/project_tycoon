package be.projecttycoon.rest;

import be.projecttycoon.db.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thomas on 10/02/16.
 */
@RestController
public class GameResource {

    private final GameRepository gameRepository;

    @Autowired
    public GameResource(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

}
