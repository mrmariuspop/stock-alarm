package stockv1.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stockv1.demo.model.User;

/**
 * @author Marius Pop
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
