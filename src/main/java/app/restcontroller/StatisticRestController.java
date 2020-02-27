package app.restcontroller;

import app.model.Statistic;
import app.service.statistic.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/statistic")
public class StatisticRestController {

    private StatisticService statisticService;

    public StatisticRestController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public ResponseEntity<Statistic> getStatistic() {
        return new ResponseEntity<>(statisticService.getStatistic(), HttpStatus.OK);
    }
}
