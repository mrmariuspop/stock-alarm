package stockv1.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stockv1.demo.model.Alarm;

import java.util.List;

/**
 * @author Marius Pop
 */
@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findByStockSymbol(String stockSymbol);
}
