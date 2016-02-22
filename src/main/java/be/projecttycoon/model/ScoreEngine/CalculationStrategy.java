package be.projecttycoon.model.ScoreEngine;

import be.projecttycoon.model.KnowledgeAreaScore;
import be.projecttycoon.model.Question;

/**
 * Created by Jeroen on 19-2-2016.
 */
public interface CalculationStrategy {
    public void calculateScore(KnowledgeAreaScore knowledgeAreaScore, Question question);
}
