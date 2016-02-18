package be.projecttycoon.rest;

import be.projecttycoon.db.GameRepository;
import be.projecttycoon.db.QuestionRepository;
import be.projecttycoon.model.Game;
import be.projecttycoon.model.KnowledgeArea;
import be.projecttycoon.model.Question;
import be.projecttycoon.rest.exception.NotFoundException;
import be.projecttycoon.rest.util.GameBean;
import be.projecttycoon.rest.util.QuestionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.Collection;

/**
 * Created by Jeroen on 17-2-2016.
 */
@RestController
@RequestMapping(value = "/api/questions")
public class QuestionResource {

    private final QuestionRepository questionRepository;



    @Autowired
    public QuestionResource(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json")
    public Collection<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces("application/json")
    public Question getQuestion(@PathVariable long id ){
        Question question = questionRepository.findOne(id);
        if(question == null)
            throw new NotFoundException();
        return question;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Produces("application/json")
    public Question createQuestion(@Valid @RequestBody QuestionBean inputQuestion){
        Question question = new Question(inputQuestion.getQuestion(), inputQuestion.getFormat(), inputQuestion.getAnswers());
        question = questionRepository.save(question);
        return question;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Question updateQuestion(@PathVariable long id, @RequestBody Question question){
        Question q = getQuestion(id);
        q.setFormat(question.getFormat());
        q.setQuestion(question.getQuestion());
        return questionRepository.save(q);
    }

    @RequestMapping(value = "/answers/{id}", method = RequestMethod.PUT)
    public Question updateAnswers(@PathVariable long id, @RequestBody Question question){
        Question q = getQuestion(id);
        q.setAnswers(question.getAnswers());
        return questionRepository.save(q);
    }


}