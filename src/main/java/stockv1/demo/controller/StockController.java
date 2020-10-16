package stockv1.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import stockv1.demo.model.Stock;
import stockv1.demo.service.StockService;

import java.util.List;

/**
 * @author Marius Pop
 */
@RestController
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @ApiOperation("get a list of all stocks")
    @GetMapping("/stocks")
    public List<Stock> findAll() {
        return stockService.findAll();
    }

    @ApiOperation("get a stock by id")
    @GetMapping("/stock/{id}")
    public Stock findOne(@PathVariable(name = "id") Long stockId) {
        return stockService.findOne(stockId);
    }

    @ApiOperation("save stock")
    @PostMapping("/stock")
    public Stock saveStock(@RequestParam(name = "symbol") String symbol,
                           @RequestParam(name = "price") Long price) throws Exception {
        return stockService.saveStock(symbol, price);
    }

    @ApiOperation("delete stock")
    @DeleteMapping("/stock/{id}")
    public void deleteStock(@PathVariable(name = "id") Long stockId) {
        stockService.deleteStock(stockId);
    }

    @ApiOperation("update a stock price")
    @PutMapping("/stock")
    public Stock updateStock(@RequestParam(name = "symbol") String symbol,
                             @RequestParam(name = "newPrice") Long newPrice) throws Exception {
        return stockService.updateStock(symbol, newPrice);
    }
}
