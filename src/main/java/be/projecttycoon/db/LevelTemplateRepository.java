package be.projecttycoon.db;

import be.projecttycoon.model.ScoreEngineTemplate.LevelTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jeroen on 24-2-2016.
 */
public interface LevelTemplateRepository extends JpaRepository<LevelTemplate, Long>{
}
