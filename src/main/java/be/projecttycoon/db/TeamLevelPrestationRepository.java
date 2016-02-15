package be.projecttycoon.db;

import be.projecttycoon.model.TeamLevelPrestation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by thomas on 15/02/16.
 */
public interface TeamLevelPrestationRepository extends JpaRepository<TeamLevelPrestation, Long> {
}
