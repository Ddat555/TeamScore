package org.teamscore.individualTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamscore.individualTask.models.DTO.statistic.StatisticRequest;
import org.teamscore.individualTask.services.StatisticService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/summary")
    public ResponseEntity<?> getSummaryStatistic(@RequestParam(name = "from") LocalDateTime from, @RequestParam("to") LocalDateTime to){
        var result = statisticService.getStatisticByPeriod(from,to);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategoryStatistic(@RequestParam(name = "from")LocalDateTime from, @RequestParam("to") LocalDateTime to){
        var result = statisticService.getCategoryStatisticByPeriod(from, to);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/type-payment")
    public ResponseEntity<?> getTypePaymentStatistic(@RequestParam(name = "from") LocalDateTime from, @RequestParam("to") LocalDateTime to){
        var result = statisticService.getTypePaymentStatisticByPeriod(from, to);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/top-seller")
    public ResponseEntity<?> getTopSellers(@RequestParam(name = "from") LocalDateTime from, @RequestParam("to") LocalDateTime to){
        var result = statisticService.getSellerStatisticByPeriod(from, to);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
