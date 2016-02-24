package be.projecttycoon.rest.admin;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Team;
import be.projecttycoon.rest.util.MailBean;
import be.projecttycoon.social.GmailRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by kiwi on 18/02/2016.
 */

@RestController
@RequestMapping("/api/admin/mail")
public class MailController {

    @Autowired
    TeamRepository teamrep;
    @Autowired
    GameRepository gamerep;
    @Autowired
    GmailRest gmail;


    @RequestMapping(value="/{gameId}", method = RequestMethod.POST)
    public void sendMail(@PathVariable long gameId, @RequestBody MailBean mail, Principal user){
        try {
            Team principal = teamrep.findByTeamname(user.getName());
            if (principal.isAdmin()) {

                List<Team> teams = mail.getRecipients();
                teams.add(principal);
                Set<String> recipients = teams.stream()
                        .map(t -> t.getEmail())
                        .filter(e -> e != null)
                        .collect(Collectors.toSet());
                gmail.sendEmail(recipients, mail.getSubject(), mail.getMessage());
            } else {

            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
