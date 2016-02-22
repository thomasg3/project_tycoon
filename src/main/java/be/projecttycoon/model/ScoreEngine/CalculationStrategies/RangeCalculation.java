package be.projecttycoon.model.ScoreEngine.CalculationStrategies;

import be.projecttycoon.model.Answer;
import be.projecttycoon.model.KnowledgeAreaScore;
import be.projecttycoon.model.Question;
import be.projecttycoon.model.ScoreEngine.CalculationStrategy;
import be.projecttycoon.model.ScoreEngine.util.Between;
import be.projecttycoon.model.ScoreEngine.util.Cleaner;
import be.projecttycoon.model.ScoreEngine.util.Splitter;
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
            String cleanInput = Cleaner.clean(answer.getAnswer());
            String[] split = Splitter.split(cleanInput);

            answers.add(new Between(Integer.valueOf(split[0]),Integer.valueOf(split[1]), answer.getScore()));
        }

        for (Between between: answers) {
            if(between.isBetween(Integer.valueOf(Cleaner.clean(knowledgeAreaScore.getAnswer())))){
                score = between.getScore();
            }
        }
        knowledgeAreaScore.setScore(score);
    }

}