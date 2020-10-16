package stockv1.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import stockv1.demo.model.Alarm;
import stockv1.demo.service.AlarmService;

import java.util.List;

/**
 * @author Marius Pop
 */
@RestController
public class AlarmController {

    private final AlarmService alarmService;

    public AlarmController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @ApiOperation("get a list of alarms")
    @GetMapping("/alarms")
    public List<Alarm> findAll() {
        return alarmService.findAll();
    }

    @ApiOperation("get alarm by id")
    @GetMapping("/alarm/{id}")
    public Alarm findOne(@PathVariable(name = "id") Long id) {
        return alarmService.findOne(id);
    }

    @ApiOperation("create an alarm")
    @PostMapping("/alarm")
    public Alarm saveAlarm(@RequestParam(name = "stockSymbol") String stockSymbol,
                           @RequestParam(name = "lowerThreshold") Long lower,
                           @RequestParam(name = "upperThreshold") Long upper) throws Exception {
        return alarmService.saveAlarm(stockSymbol, lower, upper);
    }

    @ApiOperation("update an alarm")
    @PutMapping("/alarm")
    public Alarm updateAlarm(@RequestBody Alarm alarm) {
        return alarmService.updateAlarm(alarm);
    }

    @ApiOperation("update an alarm")
    @DeleteMapping("/alarm/{id}")
    public void deleteAlarm(@PathVariable(name = "id") Long id) {
        alarmService.deleteAlarm(id);
    }


}
