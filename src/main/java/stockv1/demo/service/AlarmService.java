package stockv1.demo.service;

import org.springframework.stereotype.Service;
import stockv1.demo.model.Alarm;
import stockv1.demo.model.Stock;
import stockv1.demo.repository.AlarmRepository;
import stockv1.demo.repository.StockRepository;

import java.util.List;

import static stockv1.demo.utils.Constants.*;

/**
 * @author Marius Pop
 */
@Service
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final StockRepository stockRepository;

    public AlarmService(AlarmRepository alarmRepository, StockRepository stockRepository) {
        this.alarmRepository = alarmRepository;
        this.stockRepository = stockRepository;
    }

    public Alarm findOne(Long id) {
        return alarmRepository.findOne(id);
    }

    public List<Alarm> findAll() {
        return alarmRepository.findAll();
    }

    public Alarm saveAlarm(String symbol, Long lower, Long upper) throws Exception {
        Stock bySymbol = stockRepository.findBySymbol(symbol);
        if (bySymbol == null) {
            throw new Exception(STOCK_NOT_FOUND);
        }
        List<Alarm> byStockSymbol = alarmRepository.findByStockSymbol(symbol);
        if (!byStockSymbol.isEmpty()) {
            throw new Exception(ALARM_UNICITY_CONSTRAINT);
        }
        Alarm alarm = new Alarm();
        alarm.setStatus(ACTIVE);
        alarm.setLowerThreshold(lower);
        alarm.setUpperThreshold(upper);
        alarm.setStockSymbol(symbol);

        bySymbol.setAlarmId(alarm.getId());
        updateStockWithAlarm(bySymbol, alarmRepository.save(alarm));
        return alarm;
    }

    public Alarm updateAlarm(Alarm alarm) {
        return alarmRepository.save(alarm);
    }

    public void deleteAlarm(Long id) {
        alarmRepository.delete(id);
    }

    public void updateStockWithAlarm(Stock stock, Alarm alarm) {
        Stock stockToBeUpdated = stockRepository.findOne(stock.getId());
        Alarm alarmToBeSet = alarmRepository.findOne(alarm.getId());
        stockToBeUpdated.setAlarmId(alarmToBeSet.getId());
        stockRepository.save(stock);
    }
}
