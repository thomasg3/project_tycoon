package be.projecttycoon.db;

import be.projecttycoon.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jeroen on 17-2-2016.
 */
public interface QuestionRepository extends JpaRepository<Question,Long>{
}