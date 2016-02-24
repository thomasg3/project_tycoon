package be.projecttycoon.db;

import be.projecttycoon.model.ScoreEngineTemplate.ScoreEngineTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jeroen on 23-2-2016.
 */
public interface ScoreEngineTemplateRepository extends JpaRepository<ScoreEngineTemplate, Long> {
}
