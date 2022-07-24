package com.palahno.candleservice;

import com.palahno.candleservice.dto.Stock;
import com.palahno.candleservice.entity.Candle;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author Artem Palahno
 */

public class TestUtils {

    public static Candle buildCandle(String name) {
        Candle candle = new Candle();
        candle.setName(name);
        return candle;
    }

    public static Stock buildStock(String name) {
        Stock stock = new Stock();
        stock.setSymbol(name);
        stock.setPrice(BigDecimal.ONE);
        stock.setDateTime(Instant.now());
        stock.setVersion("version");
        return stock;
    }
}
