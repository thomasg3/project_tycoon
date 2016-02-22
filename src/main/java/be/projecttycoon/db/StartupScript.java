package be.projecttycoon.db;

import be.projecttycoon.model.*;
import be.projecttycoon.model.ScoreEngine.ScoreFormat;
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
    private final InfoRepository infoRepository;


    @Autowired
    public StartupScript(GameRepository gameRepository, KnowledgeAreaRepository knowledgeAreaRepository, LevelRepository levelRepository, QuestionRepository questionRepository, TeamLevelPrestationRepository teamLevelPrestationRepository, TeamRepository teamRepository, InfoRepository infoRepository) {
        this.gameRepository = gameRepository;
        this.knowledgeAreaRepository = knowledgeAreaRepository;
        this.levelRepository = levelRepository;
        this.questionRepository = questionRepository;
        this.teamLevelPrestationRepository = teamLevelPrestationRepository;
        this.teamRepository = teamRepository;
        this.infoRepository=infoRepository;
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

        Game testgame = new Game("Met Questions", 5,5, knowledgeAreaRepository.findAll());
        ArrayList<Team> teams2= new ArrayList<Team>();
        teams2.addAll(testgame.getTeams());
        teams2.get(0).setTeamname("Team123");
        teams2.get(0).setPassword("azerty");

        for(Level l:testgame.getLevels()){
            for(LevelKnowledgeArea lk : l.getLevelKnowledgeAreas()){
                lk.getQuestion().setQuestion("Dit is een vraag... met als antwoord 'test' 50 en 'testtest' 30");
                lk.getQuestion().setFormat(ScoreFormat.STRING);
                List<Answer> answers = new ArrayList<>();
                answers.add(new Answer("test", 50));
                answers.add(new Answer("testtest", 30));
                lk.getQuestion().setAnswers(answers);
            }
        }

        gameRepository.save(testgame);

        Game test = new Game("ScoreEngine tester", 5,5, knowledgeAreaRepository.findAll());
        ArrayList<Team> teams22= new ArrayList<Team>();
        teams22.addAll(test.getTeams());
        teams22.get(0).setTeamname("PerfectScore");
        teams22.get(0).setPassword("azerty");

        teams22.get(1).setTeamname("WorstScore");
        teams22.get(1).setPassword("azerty");

        teams22.get(2).setTeamname("MediumScore");
        teams22.get(2).setPassword("azerty");


        for(KnowledgeAreaScore kas :  teams22.get(0).getTeamLevelPrestations().get(0).getKnowledgeAreaScores()){
            kas.setAnswer("5-5-9");
        }

        for(KnowledgeAreaScore kas :  teams22.get(2).getTeamLevelPrestations().get(0).getKnowledgeAreaScores()){
            kas.setAnswer("5-5-7");
        }

        for(KnowledgeAreaScore kas :  teams22.get(1).getTeamLevelPrestations().get(0).getKnowledgeAreaScores()){
            kas.setAnswer("5-5-6");
        }


        for(Level l:test.getLevels()){
            for(LevelKnowledgeArea lk : l.getLevelKnowledgeAreas()){
                lk.getQuestion().setQuestion("Dit is een vraag... met als antwoord 'test' 50 en 'testtest' 30");
                lk.getQuestion().setFormat(ScoreFormat.ENUMERATION);
                List<Answer> answers = new ArrayList<>();
                answers.add(new Answer("5-5-9", 50));
                answers.add(new Answer("5-5-*", 10));
                answers.add(new Answer("5-7-8", -20));
                lk.getQuestion().setAnswers(answers);
            }
        }

        gameRepository.save(test);

        Game testgame2 = new Game("testGame123342", 5,5, knowledgeAreaRepository.findAll());
        ArrayList<Team> teams3= new ArrayList<Team>();
        teams2.addAll(testgame.getTeams());
        teams2.get(0).setTeamname("Team123");
        teams2.get(0).setPassword("azerty");
        teams2.get(0).setRegistered(true);


        gameRepository.save(testgame2);
        gameRepository.save(game);


        Game scoreTest = new Game("The Admin Games", 4, 8, knowledgeAreaRepository.findAll());
        teams = new ArrayList<>();
        teams.addAll(scoreTest.getTeams());
        teams.get(0).setTeamname("ABCDEFGH");
        teams.get(1).setTeamname("DeVrolijkeBarten");
        teams.get(2).setTeamname("ProjectNinas");
        teams.get(3).setTeamname("TeamWin");
        teams.get(0).setRegistered(true);
        teams.get(1).setRegistered(true);
        teams.get(2).setRegistered(true);
        teams.get(3).setRegistered(true);

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

        Info i = new Info(1,"test info", "http://i.imgur.com/1rHMtFM.gif", InfoType.Image);
        Info i2 = new Info(1,"test video","https://www.youtube.com/embed/czezOcHfLS4",InfoType.Video);
        infoRepository.save(i);
        infoRepository.save(i2);

    }
}
