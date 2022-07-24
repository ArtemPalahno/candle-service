package com.palahno.candleservice.service;

import com.palahno.candleservice.dto.CandleConfig;
import com.palahno.candleservice.entity.Candle;
import com.palahno.candleservice.dto.Stock;
import com.palahno.candleservice.repository.CandleRepository;
import com.palahno.candleservice.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Artem Palahno
 */
@Service
@RequiredArgsConstructor
public class CandleServiceImpl implements CandleService {

    private final CandleRepository candleRepository;
    private final CandleConfig candleConfig;

    @Override
    public void formCandle(Stock stock) {
        LinkedList<Candle> candleList = candleRepository.findAllByName(stock.getSymbol());
        Candle candleToSave;
        if (!candleList.isEmpty()) {
            Candle newestCandle = candleList.getLast();
            if (TimeUtil.compare(stock.getDateTime(), newestCandle.getCloseTime(), candleConfig.getType(), candleConfig.getCount()) >= 0) {
                candleToSave = new Candle(stock);
            } else {
                candleToSave = updateWithNewStock(newestCandle, stock);
            }
        } else {
            candleToSave = new Candle(stock);
        }
        candleRepository.save(candleToSave);
    }

    @Override
    public List<Candle> findAllByName(String name) {
        return candleRepository.findAllByName(name);
    }

    private Candle updateWithNewStock(Candle candle, Stock stock) {
        BigDecimal stockPrice = stock.getPrice();
        if (stockPrice.compareTo(candle.getMaxPrice()) > 0) {
            candle.setMaxPrice(stockPrice);
        }
        if (stockPrice.compareTo(candle.getMinPrice()) < 0) {
            candle.setMinPrice(stockPrice);
        }
        candle.setClosePrice(stockPrice);
        candle.setCloseTime(stock.getDateTime());
        return candle;
    }
}
