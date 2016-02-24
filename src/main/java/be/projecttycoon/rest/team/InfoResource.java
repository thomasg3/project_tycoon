package be.projecttycoon.rest.team;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.InfoRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.Info;
import be.projecttycoon.model.InfoType;
import be.projecttycoon.model.Team;
import be.projecttycoon.model.level.Level;
import be.projecttycoon.model.level.LevelState;
import be.projecttycoon.model.level.Open;
import be.projecttycoon.rest.exception.NotAuthorizedException;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.util.UrlBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by michael on 18/02/16.
 */

@RestController
@RequestMapping(value = "/api/info")
public class InfoResource {


    protected final InfoRepository infoRepository;
    protected final GameRepository gameRepository;
    protected final TeamRepository teamRepository;

    @Autowired
    public InfoResource(InfoRepository infoRepository, GameRepository gameRepository, TeamRepository teamRepository){
        this.infoRepository=infoRepository;
        this.gameRepository=gameRepository;
        this.teamRepository=teamRepository;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Info findOneInfo(Principal user, @PathVariable long id){
        Team team = teamRepository.findByTeamname(user.getName());
        Info info = infoRepository.findOne(id);
        if(team.isAdmin())
            return info;
        Game game = gameRepository.findAll().stream().filter(g -> g.containsTeam(team)).findFirst().orElse(null);
        if(info == null || game == null)
            throw new NotFoundException();
        if(info.getUnlockedAtLevel() <= game.openLevel() && !info.getExcludedTeams().contains(team.getId()))
            return info;
        else throw new NotAuthorizedException();

    }

    //todo make sure teams can only see their own info
    @RequestMapping(value="/team/{id}",method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Info> getInfo(@PathVariable long id){
        Team t = teamRepository.findOne(id);
        Game game = gameRepository.findAll().stream()
                .filter(g->g.getTeams().contains(t))
                .findFirst().orElse(null);
       int round = game.getLevels().stream()
                .filter(l->l.documentsAreOpen())
                .mapToInt(Level::getRound)
                .max().orElse(-1);
        Collection<Info> info = infoRepository.findAll().stream()
                .filter(i->i.getUnlockedAtLevel() <= round)
                .filter(j->!j.getExcludedTeams().contains(t.getId()))
                .collect(Collectors.toList());
        return info;
    }


}
