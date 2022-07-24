package com.palahno.candleservice.service;

import com.palahno.candleservice.entity.Candle;
import com.palahno.candleservice.dto.Stock;

import java.util.List;

/**
 * @author Artem Palahno
 */
public interface CandleService {
    /**
     * Forms candle from a {@link Stock}
     *
     * @param stock stock
     */
    void formCandle(Stock stock);

    /**
     * Finds candles by name
     *
     * @param name candle name
     * @return candles
     */
    List<Candle> findAllByName(String name);
}
