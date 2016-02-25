package be.projecttycoon.rest.team;

import be.projecttycoon.db.KnowledgeAreaScoreRepository;
import be.projecttycoon.db.TeamRepository;
import be.projecttycoon.model.KnowledgeAreaScore;
import be.projecttycoon.model.Team;
import be.projecttycoon.model.TeamLevelPrestation;
import be.projecttycoon.rest.exception.NotAuthorizedException;
import be.projecttycoon.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Jeroen on 18-2-2016.
 */
@RestController
@RequestMapping("/api/knowledgeareascores")
public class KnowledgeAreaScoreResource {

    private final KnowledgeAreaScoreRepository knowledgeAreaScoreRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public KnowledgeAreaScoreResource(KnowledgeAreaScoreRepository knowledgeAreaScoreRepository, TeamRepository teamRepository){
        this.knowledgeAreaScoreRepository = knowledgeAreaScoreRepository;
        this.teamRepository = teamRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<KnowledgeAreaScore> getAllKnowledgeAreaScores(){
        List<KnowledgeAreaScore> result =  knowledgeAreaScoreRepository.findAll();
        result.forEach(kas -> kas.setScore(0));
        return result;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public KnowledgeAreaScore getKnowledgeAreaScore(@PathVariable long id){
        KnowledgeAreaScore found =  knowledgeAreaScoreRepository.findOne(id);
        if(found == null)
            throw new NotFoundException();
        found.setScore(0);
        return found;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public KnowledgeAreaScore updateKnowledgeAreaScore(Principal user, @PathVariable long id, @RequestBody KnowledgeAreaScore knowledgeAreaScore){
        KnowledgeAreaScore knas = getKnowledgeAreaScore(id);
        Team team = teamRepository.findByTeamname(user.getName());
        TeamLevelPrestation teamLevelPrestation = team.getTeamLevelPrestations().stream()
                .filter( tlp -> tlp.getKnowledgeAreaScores().contains(knas))
                .findFirst()
                .orElseThrow(() -> new NotFoundException());
        if(team.isAdmin() || teamLevelPrestation.getLevel().questionsAreOpen()){
            knas.setAnswer(knowledgeAreaScore.getAnswer());
            return knowledgeAreaScoreRepository.save(knas);
        } else {
            throw new NotAuthorizedException();
        }

    }
}
