package be.projecttycoon.db;

import be.projecttycoon.model.level.Level;
import be.projecttycoon.model.TeamLevelPrestation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by thomas on 15/02/16.
 */
public interface TeamLevelPrestationRepository extends JpaRepository<TeamLevelPrestation, Long> {
    List<TeamLevelPrestation> findByLevel(Level level);
}
