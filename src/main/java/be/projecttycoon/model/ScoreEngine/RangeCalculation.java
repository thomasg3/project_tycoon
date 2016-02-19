package be.projecttycoon.model.ScoreEngine;

import be.projecttycoon.model.Answer;
import be.projecttycoon.model.KnowledgeAreaScore;
import be.projecttycoon.model.Question;
import be.projecttycoon.model.ScoreEngine.util.Between;
import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeroen on 19-2-2016.
 */
public class RangeCalculation implements CalculationStrategy {


    @Override
    public void calculateScore(KnowledgeAreaScore knowledgeAreaScore, Question question) {
        List<Between> answers = new ArrayList<>();
        int score = 0;
        for(Answer answer: question.getAnswers()){
            String[] split;
            if(answer.getAnswer().contains("-")){
                split = answer.getAnswer().split("-");
            }
            else if(answer.getAnswer().contains("_")){
                split = answer.getAnswer().split("_");
            }
            else if(answer.getAnswer().contains(";")){
                split = answer.getAnswer().split(";");
            }
            else if(answer.getAnswer().contains(" ")){
                split = answer.getAnswer().split(" ");
            } else {
                throw new IllegalArgumentException("String " + answer.getAnswer() + " does not contain delimiter");
            }
            answers.add(new Between(Integer.valueOf(split[0]),Integer.valueOf(split[1]), answer.getScore()));
        }

        for (Between between: answers) {
            if(between.isBetween(Integer.valueOf(knowledgeAreaScore.getAnswer()))){
                score = between.getScore();
            }
        }
        knowledgeAreaScore.setScore(score);

    }

}
