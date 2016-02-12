package be.projecttycoon;

//import be.projecttycoon.controller.TestController;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.GameResource;
import be.projecttycoon.rest.TeamResource;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.response.Cookie;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.*;


/**
 * Created by kiwi on 11/02/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProjecttycoonApplication.class)
@WebAppConfiguration
public class TeamRestTest extends SuperTest{
    @Autowired
    private TeamRepository teamRepository;


    public TeamRepository getTeamRepository(){
        return this.teamRepository;
    }

    public void setTeamRepository(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }


    public TeamRestTest() {
        RestAssured.basePath = "/api/teams";
    }


    @Test
    public void viewTeamsWhenAuthenticatedAsInvalidUserShouldBeBlocked(){
        UnauthorizedRequestSpecification.expect().statusCode(UNAUTHORIZED).when().get();
    }

    @Test
    public void viewTeamsWhenAuthenticatedAsValidUser(){
        AuthorizedRegisteredRequestSpecification.expect().statusCode(ALLOWED).when().get();
    }


    @Test
    public void searchForUserWithIdSixWhenAuthenticated(){
        AuthorizedRegisteredRequestSpecification.expect().when().
                get("/6").
                then().
                body(containsString("id")).
                body(containsString("teamname")).
                body(containsString("score")).
                body(containsString("likes")).
                body(containsString("registered")).
                body(containsString("admin")).
                body("id", equalTo(6))
                .statusCode(200);
    }


    @Test
    public void searchForUserWithIdSixWhenNotAuthenticatedShouldBeBlocked(){
        UnauthorizedRequestSpecification.expect().statusCode(UNAUTHORIZED).when().get("/6");
    }

    @Test
    public void searchForJosWhenAuthenticated(){
        AuthorizedRegisteredRequestSpecification.expect().when().
                get("/search/jos").
                then().
                body(containsString("id")).
                body(containsString("teamname")).
                body(containsString("score")).
                body(containsString("likes")).
                body(containsString("registered")).
                body(containsString("admin")).
                body("teamname", equalTo("jos"))
                .statusCode(ALLOWED);
    }


    @Test
    public void searchForJosWhenNotAuthenticatedShouldBeBlocked(){
        UnauthorizedRequestSpecification.expect().statusCode(UNAUTHORIZED).when().get("/search/jos");
    }


    @Test
    public void checkThatJosIsNotRegisteredWhenAuthenticatedAndRegister(){
        UnregisteredRequestSpecification.expect().when().
                get("/search/jos/registered").
                then().
                body("registered", equalTo(false)).statusCode(ALLOWED);


        Team team = getTeamRepository().findByTeamname("jos");
        team.register("jos", "jos", "");
        getTeamRepository().save(team);



        //He isn't unregistered anymore... /giphy Mind Blown!
        //Note: dit kan alle testen stuk makenen gezien onze unregistered user nu registered is...
        UnregisteredRequestSpecification.expect().when().
                get("/search/jos/registered").
                then().
                body("registered", equalTo(false)).statusCode(ALLOWED);
    }

    @Test
    public void checkThatJefIsRegisteredWhenAuthenticated(){
        AuthorizedRegisteredRequestSpecification.expect().when().
                get("/search/jef/registered").
                then().
                body("registered", equalTo(true)).statusCode(ALLOWED);
    }

    @Test
    public void checkThatJosIsNotRegisteredWhenNotAuthenticatedShouldBeBlocked(){
        UnauthorizedRequestSpecification.expect().statusCode(UNAUTHORIZED).when().get("/search/jos/registered");
    }
}
