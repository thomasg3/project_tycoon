package be.projecttycoon.db;

import be.projecttycoon.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by thomas on 09/02/16.
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByTeamname(String teamname);
}

