package com.palahno.candleservice.controller;

import com.palahno.candleservice.entity.Candle;
import com.palahno.candleservice.exception.NoSuchCandlesException;
import com.palahno.candleservice.service.CandleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Artem Palahno
 */
@RestController
@RequiredArgsConstructor
public class CandleController {

    private final CandleService candleService;

    @GetMapping("/{stockName}/candles")
    public List<Candle> findCandlesByName(@PathVariable("stockName") String name) {
        List<Candle> candles = candleService.findAllByName(name);
        if (candles.isEmpty()){
            throw new NoSuchCandlesException();
        }
        return candles;
    }
}
