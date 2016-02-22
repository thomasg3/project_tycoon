package be.projecttycoon.db;

import be.projecttycoon.model.Stakeholder;
import be.projecttycoon.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.faces.component.StateHolder;

/**
 * Created by kiwi on 17/02/2016.
 */
public interface StakeholderRepository extends JpaRepository<Stakeholder, Long> {
    //todo Hebben we deze nodig?
    Stakeholder findByName(String name);
}
