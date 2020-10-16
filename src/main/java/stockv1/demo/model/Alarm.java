package stockv1.demo.model;

import javax.persistence.*;

/**
 * @author Marius Pop
 */
@Entity
@Table(name = "alarm")
public class Alarm {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "stock_symbol")
    private String stockSymbol;

    //in case the price of stock is over +upperThreshold%
    @Column(name = "upper_threshold")
    private Long upperThreshold;

    //in case the price of stock is under -lowerThreshold%
    @Column(name = "lower_threshold")
    private Long lowerThreshold;

    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public Long getUpperThreshold() {
        return upperThreshold;
    }

    public void setUpperThreshold(Long upperThreshold) {
        this.upperThreshold = upperThreshold;
    }

    public Long getLowerThreshold() {
        return lowerThreshold;
    }

    public void setLowerThreshold(Long lowerThreshold) {
        this.lowerThreshold = lowerThreshold;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "id=" + id +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", upperThreshold=" + upperThreshold +
                ", lowerThreshold=" + lowerThreshold +
                '}';
    }
}
