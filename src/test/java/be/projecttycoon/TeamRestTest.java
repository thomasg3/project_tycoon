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


    private RequestSpecification createAdminRequestSpecification(){
        return given().auth().basic("jos", "jos").contentType("application/json");
    }

    @Test
    public void viewTeamsWhenAuthenticatedAsInvalidUser(){
        given().auth().basic("username", "wrongpassword").expect().statusCode(401).when().get();
    }

    @Test
    public void viewTeamsWhenAuthenticatedAsValidUser(){
        adminRequestSpecification.expect().statusCode(200).when().get();
    }


    @Test
    public void checkIfUserWithIdOneIsJos(){
        adminRequestSpecification.expect().when().
                get("/1").
                then().
                body(containsString("id")).
                body(containsString("teamname")).
                body(containsString("score")).
                body(containsString("likes")).
                body(containsString("registered")).
                body(containsString("admin")).
                body("id", equalTo(1)).
                body("teamname", equalTo("jos"))
                .statusCode(200);
    }


    @Test
    public void searchForJos(){
        adminRequestSpecification.expect().when().
                get("/search/jos").
                then().
                body(containsString("id")).
                body(containsString("teamname")).
                body(containsString("score")).
                body(containsString("likes")).
                body(containsString("registered")).
                body(containsString("admin")).
                body("id", equalTo(1)).
                body("teamname", equalTo("jos"))
                .statusCode(200);
    }


    @Test
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

        Headers xcsrf = adminRequestSpecification.expect().when().get("/search/jos").getHeaders();
        String newToken=null;
        for(Header hdr:xcsrf){
            System.out.println(hdr);
            if(hdr.getValue().contains("XSRF-TOKEN")){
                String[] values= hdr.getValue().split("Set-Cookie=");
                newToken = values[0].substring(0, values[0].length());
                break;
            }
        }
        System.out.println("The extracted token is: " + newToken);
        Cookie cookie = new Cookie.Builder("XSRF-TOKEN",newToken).build();

        //When Jos Updates, registered is set to true
        adminRequestSpecification.cookie(cookie).
                 body("{\"oldUsername\":\"jos\",\"oldPassword\":\"jos\",\"newUsername\":\"jos\",\"newPassword\":\"jos\"}")
                .when()
                .put("/1");

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
    }





}
