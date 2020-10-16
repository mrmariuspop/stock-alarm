package stockv1.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stockv1.demo.model.EncryptedPassword;

import java.util.List;

/**
 * @author Marius Pop
 */
@Repository
public interface EncryptedPasswordRepository extends JpaRepository<EncryptedPassword, Long> {
    List<EncryptedPassword> findAll();
}
