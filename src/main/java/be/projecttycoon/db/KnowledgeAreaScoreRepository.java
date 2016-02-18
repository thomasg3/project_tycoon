package be.projecttycoon.db;

import be.projecttycoon.model.KnowledgeAreaScore;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jeroen on 18-2-2016.
 */
public interface KnowledgeAreaScoreRepository extends JpaRepository<KnowledgeAreaScore, Long>{
}
