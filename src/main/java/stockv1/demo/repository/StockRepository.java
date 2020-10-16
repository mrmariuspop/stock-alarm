package stockv1.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stockv1.demo.model.Stock;

/**
 * @author Marius Pop
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findBySymbol(String symbol);
}
