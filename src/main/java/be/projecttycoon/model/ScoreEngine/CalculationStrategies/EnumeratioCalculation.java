package be.projecttycoon.model.ScoreEngine.CalculationStrategies;

import be.projecttycoon.model.Answer;
import be.projecttycoon.model.KnowledgeAreaScore;
import be.projecttycoon.model.Question;
import be.projecttycoon.model.ScoreEngine.CalculationStrategy;
import be.projecttycoon.model.ScoreEngine.util.Between;
import be.projecttycoon.model.ScoreEngine.util.Cleaner;
import be.projecttycoon.model.ScoreEngine.util.Enumeration;
import be.projecttycoon.model.ScoreEngine.util.Splitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 19-2-2016.
 */
public class EnumeratioCalculation implements CalculationStrategy {

    @Override
    public void calculateScore(KnowledgeAreaScore knowledgeAreaScore, Question question) {
        List<Enumeration> answers = new ArrayList<>();
        int score = 0;
        for(Answer answer: question.getAnswers()){
            String[] split = Splitter.split(Cleaner.clean(answer.getAnswer()));
            answers.add(new Enumeration(answer.getScore(), split));
        }

        for(Enumeration enumeration: answers){
            if(enumeration.match(knowledgeAreaScore.getAnswer())){
                System.out.println(knowledgeAreaScore.getAnswer() + " ==== " + enumeration.toString());
                score = enumeration.getScore();

                if(knowledgeAreaScore.getScore() == 0){
                    knowledgeAreaScore.setScore(score);
                } else if(score > knowledgeAreaScore.getScore()){
                    knowledgeAreaScore.setScore(score);
                }
                System.out.println(knowledgeAreaScore.getScore());

            }
        }


    }
}
