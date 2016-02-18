package be.projecttycoon.rest.admin;

import be.projecttycoon.db.QuestionRepository;
import be.projecttycoon.rest.team.QuestionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
