package stockv1.demo.service;

import org.springframework.stereotype.Service;
import stockv1.demo.model.Alarm;
import stockv1.demo.model.Stock;
import stockv1.demo.repository.StockRepository;

import java.util.Date;
import java.util.List;

import static stockv1.demo.utils.Constants.*;

/**
 * @author Marius Pop
 */
@Service
public class StockService {

    private final StockRepository stockRepository;
    private final EmailService emailService;
    private final AlarmService alarmService;

    public StockService(StockRepository stockRepository, EmailService emailService, AlarmService alarmService) {
        this.stockRepository = stockRepository;
        this.emailService = emailService;
        this.alarmService = alarmService;
    }

    public Stock saveStock(String symbol, Long price) throws Exception {
        if (stockRepository.findBySymbol(symbol) != null) {
            throw new Exception(STOCK_EXISTS);
        }
        //create the stock object
        Stock stock = new Stock();
        stock.setPrice(price);
        stock.setSymbol(symbol);
        stock.setCreationDate(new Date(System.currentTimeMillis()));
        stock.setLastEdited(new Date(System.currentTimeMillis()));
        return stockRepository.save(stock);
    }

    public void deleteStock(Long stockId) {
        stockRepository.delete(stockId);
    }

    public Stock findOne(Long id) {
        return stockRepository.findOne(id);
    }

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public Stock updateStock(String symbol, Long newPrice) throws Exception {
        Stock initialStock = stockRepository.findBySymbol(symbol);
        if (initialStock == null) {
            throw new Exception(STOCK_NOT_FOUND);
        }
        //save old price in a variable so we can compute the difference in %
        Long oldPrice = initialStock.getPrice();

        //set the new price of the stock
        initialStock.setPrice(newPrice);
        initialStock.setLastEdited(new Date(System.currentTimeMillis()));

        //find corresponding alarm
        Alarm alarmToBeTriggered = alarmService.findOne(initialStock.getAlarmId());

        if (checkThreshold(oldPrice, newPrice, alarmToBeTriggered.getUpperThreshold(), alarmToBeTriggered.getLowerThreshold())) {
            emailService.send(MY_EMAIL_ADDRESS, PRICE_CHANGE_SUBJECT, "The stock you are monitoring has changed.\n" +
                    "\n" +
                    "Stock symbol: " + initialStock.getSymbol()+"\n"+
                    "From initial value: " + oldPrice+"\n"+
                    "To current value: "+ newPrice);
        }

        //set the alarm to inactive after trigger and update
        alarmToBeTriggered.setStatus(INACTIVE);
        alarmService.updateAlarm(alarmToBeTriggered);

        //save the updated stock
        return stockRepository.save(initialStock);
    }

    //method to check whether the percentage exceeds the lower and upper limit
    public static boolean checkThreshold(Long oldPrice, Long newPrice, Long upperLimit, Long lowerLimit) {
        int x = (int) ((100*(newPrice-oldPrice))/oldPrice);
        if (x > 0 && x > upperLimit) return true;
        if (x < 0 && (-1)*x > lowerLimit) return true;
        else return false;
    }
}
