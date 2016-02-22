package be.projecttycoon.db;

import be.projecttycoon.model.ScoreEngine.ScoreEngine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jeroen on 19-2-2016.
 */
public interface ScoreEngineRepository extends JpaRepository<ScoreEngine, Long>{

}
