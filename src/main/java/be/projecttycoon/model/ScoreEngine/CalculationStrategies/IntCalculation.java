package be.projecttycoon.model.ScoreEngine.CalculationStrategies;

import be.projecttycoon.model.Answer;
import be.projecttycoon.model.KnowledgeAreaScore;
import be.projecttycoon.model.Question;
import be.projecttycoon.model.ScoreEngine.CalculationStrategy;

/**
 * Created by Jeroen on 19-2-2016.
 */
public class IntCalculation implements CalculationStrategy{


    @Override
    public void calculateScore(KnowledgeAreaScore knowledgeAreaScore, Question question) {
        int score = 0;
        try{
            int teamanswer = (Integer.valueOf(knowledgeAreaScore.getAnswer()));
            for(Answer answer : question.getAnswers()){
                int expected = Integer.valueOf(answer.getAnswer());
                if(teamanswer == expected){
                    score = answer.getScore();

                }
            }
        } catch (Exception e){
           // e.printStackTrace();
        }
        knowledgeAreaScore.setScore(score);
    }
}
