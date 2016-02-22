package be.projecttycoon.db;

import be.projecttycoon.model.*;
import be.projecttycoon.model.ScoreEngine.ScoreEngine;
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
    private final ScoreEngineRepository scoreEngineRepository;

    @Autowired
    public StartupScript(GameRepository gameRepository, KnowledgeAreaRepository knowledgeAreaRepository, LevelRepository levelRepository, QuestionRepository questionRepository, TeamLevelPrestationRepository teamLevelPrestationRepository, TeamRepository teamRepository, ScoreEngineRepository scoreEngineRepository) {
        this.gameRepository = gameRepository;
        this.knowledgeAreaRepository = knowledgeAreaRepository;
        this.levelRepository = levelRepository;
        this.questionRepository = questionRepository;
        this.teamLevelPrestationRepository = teamLevelPrestationRepository;
        this.teamRepository = teamRepository;
        this.scoreEngineRepository = scoreEngineRepository;
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

        ScoreEngine scoreEngine1 = new ScoreEngine("ScoreEngine1", 5,knowledgeAreaRepository.findAll());
        ScoreEngine scoreEngine2 = new ScoreEngine("ScoreEngine2", 2,knowledgeAreaRepository.findAll());
        ScoreEngine scoreEngine3 = new ScoreEngine("ScoreEngine3", 8,knowledgeAreaRepository.findAll());
        ScoreEngine scoreEngine4 = new ScoreEngine("ScoreEngine4", 3,knowledgeAreaRepository.findAll());

        scoreEngine3.getLevels().get(0).setState(Concluded.class.getSimpleName());
        scoreEngine3.getLevels().get(1).setState(Concluded.class.getSimpleName());
        scoreEngine3.getLevels().get(2).setState(Cermonie.class.getSimpleName());
        scoreEngine3.getLevels().get(3).setState(Finished.class.getSimpleName());
        scoreEngine3.getLevels().get(4).setState(Finished.class.getSimpleName());
        scoreEngine3.getLevels().get(5).setState(Open.class.getSimpleName());
        scoreEngine3.getLevels().get(6).setState(Open.class.getSimpleName());
        scoreEngine3.getLevels().get(7).setState(Closed.class.getSimpleName());

        scoreEngineRepository.save(scoreEngine1);
        scoreEngineRepository.save(scoreEngine2);
        scoreEngineRepository.save(scoreEngine3);
        scoreEngineRepository.save(scoreEngine4);

        List<ScoreEngine> scoreEngines = scoreEngineRepository.findAll();

        Game test = new Game("ScoreEngine tester", 5, scoreEngines.get(0));

        ArrayList<Team> teams22= new ArrayList<Team>();
        teams22.addAll(test.getTeams());
        teams22.get(0).setTeamname("PerfectScore");
        teams22.get(0).setPassword("azerty");
        teams22.get(0).setRegistered(true);

        teams22.get(1).setTeamname("WorstScore");
        teams22.get(1).setPassword("azerty");
        teams22.get(1).setRegistered(true);

        teams22.get(2).setTeamname("MediumScore");
        teams22.get(2).setPassword("azerty");
        teams22.get(2).setRegistered(true);


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


        Game scoreTest = new Game("The Admin Games", 4, scoreEngines.get(2));
        List<Team>teams = new ArrayList<>();
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
    }
}
