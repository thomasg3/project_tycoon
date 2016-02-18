package be.projecttycoon.db;

import be.projecttycoon.model.Game;
import be.projecttycoon.model.KnowledgeArea;
import be.projecttycoon.model.Team;
import be.projecttycoon.model.level.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by thomas on 18/02/16.
 */
@Component
public class StartupScript {
    private final GameRepository gameRepository;
    private final KnowledgeAreaRepository knowledgeAreaRepository;
    private final LevelRepository levelRepository;
    private final QuestionRepository questionRepository;
    private final TeamLevelPrestationRepository teamLevelPrestationRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public StartupScript(GameRepository gameRepository, KnowledgeAreaRepository knowledgeAreaRepository, LevelRepository levelRepository, QuestionRepository questionRepository, TeamLevelPrestationRepository teamLevelPrestationRepository, TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.knowledgeAreaRepository = knowledgeAreaRepository;
        this.levelRepository = levelRepository;
        this.questionRepository = questionRepository;
        this.teamLevelPrestationRepository = teamLevelPrestationRepository;
        this.teamRepository = teamRepository;
    }

    public void run(){

        //Admin
        Team admin = new Team("admin", "admin", new ArrayList<>());
        admin.setAdmin(true);
        teamRepository.save(admin);

        //K.A.s
        String[] areas = {"Integration", "Scope", "Time", "Cost", "Quality", "Human Resources", "Communications", "Risk", "Procurement", "Stakeholder"};
        for(int i=0; i<areas.length; i++){
            knowledgeAreaRepository.save(new KnowledgeArea(areas[i], i));
        }


        Game game = new Game("ProjectFun2016",2,4, knowledgeAreaRepository.findAll());
        ArrayList<Team> teams= new ArrayList<Team>();
        teams.addAll(game.getTeams());
        teams.get(0).setTeamname("joskes");
        teams.get(0).setPassword("joskes");
        teams.get(0).setRegistered(false);
        teams.get(1).setTeamname("jefkes");
        teams.get(1).setPassword("jefkes");
        teams.get(1).setRegistered(true);

        Game testgame = new Game("testGame123", 5,5, knowledgeAreaRepository.findAll());
        ArrayList<Team> teams2= new ArrayList<Team>();
        teams2.addAll(testgame.getTeams());
        teams2.get(0).setTeamname("Team123");
        teams2.get(0).setPassword("azerty");


        Game testgame2 = new Game("testGame123342", 5,5, knowledgeAreaRepository.findAll());
        ArrayList<Team> teams3= new ArrayList<Team>();
        teams2.addAll(testgame.getTeams());
        teams2.get(0).setTeamname("Team123");
        teams2.get(0).setPassword("azerty");
        teams2.get(0).setRegistered(true);

        gameRepository.save(testgame);
        gameRepository.save(testgame2);
        gameRepository.save(game);


        Game scoreTest = new Game("The Admin Games", 4, 8, knowledgeAreaRepository.findAll());
        teams = new ArrayList<>();
        teams.addAll(scoreTest.getTeams());
        teams.get(0).setTeamname("ABCDEFGH");
        teams.get(1).setTeamname("DeVrolijkeBarten");
        teams.get(2).setTeamname("ProjectNinas");
        teams.get(3).setTeamname("TeamWin");
        Random r  = new Random();
        teams.stream().forEach(team -> {
            team.getTeamLevelPrestations().stream().forEach(p -> {
                p.getKnowledgeAreaScores().stream().forEach(kas -> {
                    kas.setScore(r.nextInt(30)-10);
                });
            });
        });
        List<Level> levels = new ArrayList<>();
        levels.addAll(scoreTest.getLevels());
        levels.get(0).setState(Concluded.class.getSimpleName());
        levels.get(1).setState(Concluded.class.getSimpleName());
        levels.get(2).setState(Cermonie.class.getSimpleName());
        levels.get(3).setState(Finished.class.getSimpleName());
        levels.get(4).setState(Finished.class.getSimpleName());
        levels.get(5).setState(Open.class.getSimpleName());
        levels.get(6).setState(Open.class.getSimpleName());
        levels.get(7).setState(Closed.class.getSimpleName());
        gameRepository.save(scoreTest);
    }
}
