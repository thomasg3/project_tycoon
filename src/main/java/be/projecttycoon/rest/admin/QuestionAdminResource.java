package be.projecttycoon.rest.admin;

import be.projecttycoon.db.QuestionRepository;
import be.projecttycoon.model.Question;
import be.projecttycoon.model.ScoreEngine.ScoreFormat;
import be.projecttycoon.rest.team.QuestionResource;
import be.projecttycoon.rest.util.EnumFormatBean;
import be.projecttycoon.rest.util.QuestionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 18/02/16.
 */
@RestController
@RequestMapping("/api/admin/questions")
public class QuestionAdminResource extends QuestionResource {

    @Autowired
    public QuestionAdminResource(QuestionRepository questionRepository) {
        super(questionRepository);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Produces("application/json")
    public Question createQuestion(@Valid @RequestBody QuestionBean inputQuestion){
        Question question = new Question(inputQuestion.getQuestion(), ScoreFormat.valueOf(inputQuestion.getFormat().getFormat()), inputQuestion.getAnswers());
        return questionRepository.save(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Question updateQuestion(@PathVariable long id, @RequestBody QuestionBean question){
        Question q = getQuestion(id);
        q.setFormat(ScoreFormat.valueOf(question.getFormat().getFormat()));
        q.setQuestion(question.getQuestion());
        return questionRepository.save(q);
    }

    @RequestMapping(value = "/answers/{id}", method = RequestMethod.PUT)
    public Question updateAnswers(@PathVariable long id, @RequestBody QuestionBean question){
        Question q = getQuestion(id);
        q.setFormat(ScoreFormat.valueOf(question.getFormat().getFormat()));
        q.setAnswers(question.getAnswers());
        return questionRepository.save(q);
    }

    @RequestMapping(value = "/enum", method = RequestMethod.GET)
    @Produces("application/json")
    public List<EnumFormatBean> getFormats(){
        List<EnumFormatBean> formats = new ArrayList<>();
        ScoreFormat[] enumlist = ScoreFormat.values();
        for(ScoreFormat sf: enumlist){
            formats.add(new EnumFormatBean(sf.toString()));
        }
        return formats;
    }

}
