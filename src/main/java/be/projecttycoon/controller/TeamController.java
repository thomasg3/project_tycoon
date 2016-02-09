package be.projecttycoon.controller;
import be.projecttycoon.model.Team;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.core.MediaType;

/**
 * Created by michael on 09/02/16.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/getTeams")
public class TeamController {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Team> getTeams() {
        Collection<Team> coll = new ArrayList<Team>();
        try {
            Team t1 = new Team(1, "team1", "password", null);
            Team t2 = new Team(2, "team2", "password", null);

            coll.add(t1);
            coll.add(t2);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return coll;
    }

}
