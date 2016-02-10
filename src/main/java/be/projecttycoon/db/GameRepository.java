package be.projecttycoon.db;

import be.projecttycoon.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by michael on 10/02/16.
 */
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findById(Long id);
}
