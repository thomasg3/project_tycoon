package be.projecttycoon.db;

import be.projecttycoon.model.KnowledgeArea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by thomas on 11/02/16.
 */
public interface KnowledgeAreaRepository extends JpaRepository<KnowledgeArea, Long> {
}
