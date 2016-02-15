package be.projecttycoon.db;

import be.projecttycoon.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by thomas on 15/02/16.
 */
public interface LevelRepository extends JpaRepository<Level, Long> {
}
