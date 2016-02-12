package be.projecttycoon;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Cookie;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import static com.jayway.restassured.RestAssured.given;

/**
 * Created by kiwi on 11/02/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProjecttycoonApplication.class)
@WebAppConfiguration
public class SuperTest {
    protected final static int UNAUTHORIZED=401;
    protected final static int FORBIDDEN=403;
    protected final static int ALLOWED=200;

    protected RequestSpecification UnauthorizedRequestSpecification;
    protected RequestSpecification UnregisteredRequestSpecification;
    protected RequestSpecification AuthorizedRegisteredRequestSpecification;
    protected RequestSpecification AdminRequestSpecification;


   @Before
    public void setUp(){
        RestAssured.baseURI = "https://localhost";
        RestAssured.port = 8443;
        RestAssured.useRelaxedHTTPSValidation();
        UnauthorizedRequestSpecification = createUnauthorizedRequestSpecification();
        UnregisteredRequestSpecification =createUnregisteredRequestSpecification();
        AuthorizedRegisteredRequestSpecification=createRegisteredRequestSpecification();
        AdminRequestSpecification = createAdminRequestSpecification();
    }

    private RequestSpecification createAdminRequestSpecification(){
        //Unregistered user!!!
        return given().auth().basic("admin", "admin").contentType("application/json");
    }

    private RequestSpecification createUnregisteredRequestSpecification(){
        //Unregistered user!!!
        return given().auth().basic("jos", "jos").contentType("application/json");
    }
    private RequestSpecification createRegisteredRequestSpecification(){
        //Registered user!!!
        return given().auth().basic("jef", "jef").contentType("application/json");
    }

    private RequestSpecification createUnauthorizedRequestSpecification(){
        //unauthorized user!!!
        return given().auth().basic("username", "wrongpassword").contentType("application/json");
    }



    private Cookie getXsrfCookie(){
        Headers xcsrf = AuthorizedRegisteredRequestSpecification.expect().when().get("/search/jos").getHeaders();
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
}
