package com.palahno.candleservice.repository;

import com.palahno.candleservice.entity.Candle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.UUID;

import static com.palahno.candleservice.TestUtils.buildCandle;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Artem Palahno
 */
class CandleRepositoryImplTest {

    private CandleRepositoryImpl candleRepository;

    @BeforeEach
    void setUp() {
        candleRepository = new CandleRepositoryImpl();
    }

    @Test
    void shouldSaveCandleWhenNoSuchCandle() {
        String name = "testName";
        Candle candle = buildCandle(name);

        candleRepository.save(candle);

        LinkedList<Candle> actualCandles = candleRepository.findAllByName(name);
        assertEquals(candle, actualCandles.get(0));
    }

    @Test
    void shouldUpdateCandleWhenCandleWithSameNameIsExist() {
        String name = "testName";
        Candle candle = buildCandle(name);
        Candle candle1 = buildCandle(name);

        candleRepository.save(candle);
        candleRepository.save(candle1);

        LinkedList<Candle> actualCandles = candleRepository.findAllByName(name);
        assertEquals(1, actualCandles.size());
    }

    @Test
    void shouldSaveCandleWhenCandleWithSameNameAndDifferentIdIsExist() {
        String name = "testName";
        Candle candle = buildCandle(name);
        Candle candle1 = buildCandle(name);
        candle1.setId(UUID.randomUUID());

        candleRepository.save(candle);
        candleRepository.save(candle1);

        LinkedList<Candle> actualCandles = candleRepository.findAllByName(name);
        assertEquals(2, actualCandles.size());
    }
}