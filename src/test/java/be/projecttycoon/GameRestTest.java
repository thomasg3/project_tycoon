package be.projecttycoon;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.model.Game;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by kiwi on 11/02/2016.
 */
public class GameRestTest {

    private RequestSpecification adminRequestSpecification;

    @Autowired
    private GameRepository gameRepository;


    @Before
    public void initialize() {
        RestAssured.baseURI = "https://localhost";
        RestAssured.basePath = "/api/games";
        RestAssured.port = 8443;
        RestAssured.useRelaxedHTTPSValidation();
        adminRequestSpecification = createAdminRequestSpecification();
    }

    private RequestSpecification createAdminRequestSpecification(){
        //Registered user!!!
        return given().auth().basic("jef", "jef").contentType("application/json");
    }


    @Test
    public void getAllGamesWhenRegistered(){
        adminRequestSpecification.expect().statusCode(200).when().get();
    }

    @Test
    public void getAllGamesWhenAuthenticatedButNotRegisteredShouldBeBlocked(){
        given().auth().basic("jos", "jos").expect().statusCode(403).when().get();
    }

    @Test
    public void getAllGamesWhenNotAuthenticatedShouldBeBlocked(){
        given().auth().basic("username", "wrongpassword").expect().statusCode(401).when().get();
    }

    @Test
    public void getGameByIdWhenRegistered(){
        adminRequestSpecification.expect().statusCode(200).when().get("/1");
    }

    @Test
    public void getGameByIdWhenAuthenticatedButNotRegisteredShouldBeBlocked(){
        adminRequestSpecification.expect().statusCode(401).when().get("/1");
    }

    @Test
    public void getGameByIdWhenNotAuthenticatedShouldBeBlocked(){
        given().auth().basic("username", "wrongpassword").expect().statusCode(401).when().get("/1");
    }


    @Test
    public void createGameAndGetItWhileUnregisteredButAuthenticatedShouldBeBlocked(){
        Game game = new Game("A game",4);
        game = gameRepository.save(game);
        adminRequestSpecification.expect().statusCode(401).when().get("/"+game.getId());



    }

}
