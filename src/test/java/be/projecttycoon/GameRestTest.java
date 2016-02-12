package be.projecttycoon;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.GameResource;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by kiwi on 11/02/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProjecttycoonApplication.class)
@WebAppConfiguration
public class GameRestTest extends SuperTest{

    @Autowired
    private GameRepository gameRepository;



    public GameRepository getGameRepository(){
        return gameRepository;
    }

    public void setGameRepository(GameRepository gameRepository){
        this.gameRepository=gameRepository;
    }

    public GameRestTest() {
        RestAssured.basePath = "/api/games";
    }



    @Test
    public void getAllGamesWhenRegistered(){
        AuthorizedRegisteredRequestSpecification.expect().statusCode(ALLOWED).when().get();
    }

    @Test
    public void getAllGamesWhenAuthenticatedButNotRegisteredShouldBeBlocked(){
        UnregisteredRequestSpecification.expect().statusCode(FORBIDDEN).when().get();
    }

    @Test
    public void getAllGamesWhenNotAuthenticatedShouldBeBlocked(){
        UnauthorizedRequestSpecification.expect().statusCode(UNAUTHORIZED).when().get();
    }

    @Test
    public void getGameByIdWhenRegistered(){
        AuthorizedRegisteredRequestSpecification.expect().statusCode(ALLOWED).when().get("/1");
    }


    @Test
    public void getGameByIdWhenAuthenticatedButNotRegisteredShouldBeBlocked(){
        UnregisteredRequestSpecification.expect().statusCode(FORBIDDEN).when().get("/1");
    }

    @Test
    public void getGameByIdWhenNotAuthenticatedShouldBeBlocked(){
        UnauthorizedRequestSpecification.expect().statusCode(UNAUTHORIZED).when().get("/1");
    }


    @Test
    public void createGameAndGetItWhileUnauthenticatedShouldBeBlocked(){
        Game game = new Game("A game",4);
        game = gameRepository.save(game);
        UnauthorizedRequestSpecification.expect().statusCode(UNAUTHORIZED).when().get("/"+game.getId());

    }

    @Test
    public void createGameAndGetItWhileUnregisteredShouldBeBlocked(){
        Game game = new Game("AnotherGame",5);
        game = gameRepository.save(game);
        UnregisteredRequestSpecification.expect().statusCode(FORBIDDEN).when().get("/"+game.getId());
    }

    @Test
    public void createGameAndGetItWhileRegisteredShouldBeBlocked(){
        Game game = new Game("YetAnotherGame",5);
        game = gameRepository.save(game);
        AuthorizedRegisteredRequestSpecification.expect().statusCode(ALLOWED).when().get("/"+game.getId());
    }



    //Bypassing REST api via GameRepo isn't a good idea. And gives misleading test results.
    @Test
    public void createGameAndCheckIfUsersAreInGame(){
        Game game = new Game("Final Game",3);
        game = getGameRepository().save(game);
        System.out.println("The id of the game is:" + game.getId());
        System.out.println("Het aantal teams is: " +game.getTeams().size());


        for(Team team:game.getTeams()){
            System.out.println("The teams are: "+ team.getTeamname());
        }

        for(Team team:game.getTeams()){
            AuthorizedRegisteredRequestSpecification.expect().when().
                    get("/game/"+team.getTeamname()).
                    then()

                    .statusCode(ALLOWED);
        }
    }
}
