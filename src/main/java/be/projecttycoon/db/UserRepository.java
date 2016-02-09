package be.projecttycoon.db;

import be.projecttycoon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by thomas on 09/02/16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
