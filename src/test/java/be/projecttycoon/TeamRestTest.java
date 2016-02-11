package be.projecttycoon;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.response.Cookie;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.*;


/**
 * Created by kiwi on 11/02/2016.
 */
public class TeamRestTest {
    private RequestSpecification adminRequestSpecification;

    @Before
    public void initialize() {
        RestAssured.baseURI = "https://localhost";
        RestAssured.basePath = "/api/teams";
        RestAssured.port = 8443;
        RestAssured.useRelaxedHTTPSValidation();
        adminRequestSpecification = createAdminRequestSpecification();
    }

    private Cookie getXsrfCookie(){
        Headers xcsrf = adminRequestSpecification.expect().when().get("/search/jos").getHeaders();
        String newToken=null;
        for(Header hdr:xcsrf){
            System.out.println(hdr);
            if(hdr.getValue().contains("XSRF-TOKEN")){
                String[] values= hdr.getValue().split("Set-Cookie=");
                String[] splitted = values[0].split("XSRF-TOKEN=");
                newToken = splitted[1].substring(0, splitted[1].length());
                break;
            }
        }
        System.out.println("The extracted token is: " + newToken);
        return new Cookie.Builder("XSRF-TOKEN",newToken).build();
    }


    private RequestSpecification createAdminRequestSpecification(){
        return given().auth().basic("jos", "jos").contentType("application/json");
    }


    @Test
    public void viewTeamsWhenAuthenticatedAsInvalidUserShouldBeBlocked(){
        given().auth().basic("username", "wrongpassword").expect().statusCode(401).when().get();
    }

    @Test
    public void viewTeamsWhenAuthenticatedAsValidUser(){
        adminRequestSpecification.expect().statusCode(200).when().get();
    }


    @Test
    public void searchForUserWithIdSixWhenAuthenticated(){
        adminRequestSpecification.expect().when().
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
        given().auth().basic("username", "wrongpassword").expect().statusCode(401).when().get("/6");
    }

    @Test
    public void searchForJosWhenAuthenticated(){
        adminRequestSpecification.expect().when().
                get("/search/jos").
                then().
                body(containsString("id")).
                body(containsString("teamname")).
                body(containsString("score")).
                body(containsString("likes")).
                body(containsString("registered")).
                body(containsString("admin")).
                body("teamname", equalTo("jos"))
                .statusCode(200);
    }


    @Test
    public void searchForJosWhenNotAuthenticatedShouldBeBlocked(){
        given().auth().basic("username", "wrongpassword").expect().statusCode(401).when().get("/search/jos");
    }


    @Test
    public void checkThatJosIsNotRegisteredWhenAuthenticated(){
        adminRequestSpecification.expect().when().
                get("/search/jos/registered").
                then().
                body("registered", equalTo(false)).statusCode(200);
    }

    @Test
    public void checkThatJosIsNotRegisteredWhenNotAuthenticatedShouldBeBlocked(){
        given().auth().basic("username", "wrongpassword").expect().statusCode(401).when().get("/search/jos/registered");
    }






    //Werkt niet via xsrf
   /* @Test
    public void checkRegisteringForJos(){

        //By default Jos is unregistered
        adminRequestSpecification.expect().when().
                get("/search/jos").
                then().
                body(containsString("id")).
                body(containsString("teamname")).
                body(containsString("score")).
                body(containsString("likes")).
                body(containsString("registered")).
                body(containsString("admin")).
                body("registered", equalTo(false))
                .statusCode(200);


         Cookie cookie =  getXsrfCookie()
        //When Jos Updates, registered is set to true
        adminRequestSpecification.cookie(cookie).
                 body("{\"oldUsername\":\"jos\",\"oldPassword\":\"jos\",\"newUsername\":\"jos\",\"newPassword\":\"jos\"}")
                .when()
                .put("/1");*/

        // << "HTTP/1.1 403 Forbidden[\r][\n]" ?????????




       /* adminRequestSpecification.expect().when().
                get("/search/jos").then().
                body(containsString("id")).
                body(containsString("teamname")).
                body(containsString("score")).
                body(containsString("likes")).
                body(containsString("registered")).
                body(containsString("admin")).
                body("registered", equalTo(true))
                .statusCode(200);*/
    //}





}
