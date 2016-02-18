package be.projecttycoon.rest.admin;

import be.projecttycoon.db.KnowledgeAreaRepository;
import be.projecttycoon.rest.team.KnowledgeAreaResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thomas on 18/02/16.
 */
@RestController
@RequestMapping("/api/admin/knowledgeareas")
public class KnowledgeAreaAdminResource extends KnowledgeAreaResource {

    @Autowired
    public KnowledgeAreaAdminResource(KnowledgeAreaRepository knowledgeAreaRepository) {
        super(knowledgeAreaRepository);
    }
}
