package be.projecttycoon.rest.team;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.QuestionRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.KnowledgeArea;
import be.projecttycoon.model.Question;
import be.projecttycoon.model.ScoreEngine.ScoreFormat;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.util.EnumFormatBean;
import be.projecttycoon.rest.util.GameBean;
import be.projecttycoon.rest.util.QuestionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jeroen on 17-2-2016.
 */
@RestController
@RequestMapping(value = "/api/questions")
public class QuestionResource {

    protected final QuestionRepository questionRepository;


    @Autowired
    public QuestionResource(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Question> getAllQuestions(){
        List<Question> result = questionRepository.findAll();
        result.forEach(q -> q.setAnswers(Collections.EMPTY_LIST));
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Question getQuestion(@PathVariable long id ){
        Question question = questionRepository.findOne(id);
        if(question == null)
            throw new NotFoundException();
        question.setAnswers(Collections.EMPTY_LIST);
        return question;
    }

}
