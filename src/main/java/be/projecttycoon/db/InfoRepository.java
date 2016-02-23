package be.projecttycoon.db;

import be.projecttycoon.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by michael on 18/02/16.
 */
public interface InfoRepository extends JpaRepository<Info, Long> {
}
