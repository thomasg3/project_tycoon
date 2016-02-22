package be.projecttycoon.model.ScoreEngine.CalculationStrategies;

import be.projecttycoon.model.Answer;
import be.projecttycoon.model.KnowledgeArea;
import be.projecttycoon.model.KnowledgeAreaScore;
import be.projecttycoon.model.Question;
import be.projecttycoon.model.ScoreEngine.CalculationStrategy;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Jeroen on 19-2-2016.
 */
public class StringCalculation implements CalculationStrategy {

    @Override
    public void calculateScore(KnowledgeAreaScore knowledgeAreaScore, Question question) {
        int score = 0;
        String teamanswer = knowledgeAreaScore.getAnswer();
        System.out.println("Teamantwoord: " + teamanswer + ", verwacht: " + question.getAnswers().get(0));
        for(Answer answer : question.getAnswers()){
            if(answer.getAnswer().equals(knowledgeAreaScore.getAnswer())){
                score = answer.getScore();
            }
        }
        knowledgeAreaScore.setScore(score);
    }
}
