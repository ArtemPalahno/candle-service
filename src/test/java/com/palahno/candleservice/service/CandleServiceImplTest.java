package com.palahno.candleservice.service;

import com.palahno.candleservice.dto.CandleConfig;
import com.palahno.candleservice.dto.Stock;
import com.palahno.candleservice.entity.Candle;
import com.palahno.candleservice.repository.CandleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

import static com.palahno.candleservice.TestUtils.buildCandle;
import static com.palahno.candleservice.TestUtils.buildStock;
import static java.time.temporal.ChronoField.MINUTE_OF_DAY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

/**
 * @author Artem Palahno
 */
class CandleServiceImplTest {

    @InjectMocks
    private CandleServiceImpl serviceUnderTest;

    @Mock
    private CandleRepository candleRepository;

    @Mock
    private CandleConfig candleConfig;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindCandleByName() {
        String candleName = "testName";

        LinkedList<Candle> expectedResult = new LinkedList<>();
        expectedResult.add(buildCandle(candleName));

        when(candleRepository.findAllByName(candleName)).thenReturn(expectedResult);

        List<Candle> actualResult = serviceUnderTest.findAllByName(candleName);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldFormCandleWhenNoSuchCandle() {
        String name = "testName";
        Stock stock = buildStock(name);

        when(candleRepository.findAllByName(name)).thenReturn(new LinkedList<>());
        when(candleConfig.getCount()).thenReturn(1);
        when(candleConfig.getType()).thenReturn(MINUTE_OF_DAY);

        serviceUnderTest.formCandle(stock);

        Mockito.verify(candleRepository).save(any(Candle.class));
    }

    @Test
    void shouldFormCandleWhenCandleExistWithinSameTimeLap() {
        String name = "testName";
        Stock stock = buildStock(name);

        LinkedList<Candle> candles = new LinkedList<>();
        candles.add(new Candle(stock));

        when(candleRepository.findAllByName(name)).thenReturn(candles);
        when(candleConfig.getCount()).thenReturn(1);
        when(candleConfig.getType()).thenReturn(MINUTE_OF_DAY);

        serviceUnderTest.formCandle(stock);

        Mockito.verify(candleRepository).save(any(Candle.class));
    }

    @Test
    void shouldFormCandleWhenCandleExistNotInSameTimeLap() {
        String name = "testName";
        Stock stock = buildStock(name);

        LinkedList<Candle> candles = new LinkedList<>();
        Candle candle = new Candle(stock);
        candle.setCloseTime(Instant.now().plus(10, ChronoUnit.HOURS));
        candles.add(candle);

        when(candleRepository.findAllByName(name)).thenReturn(candles);
        when(candleConfig.getCount()).thenReturn(1);
        when(candleConfig.getType()).thenReturn(MINUTE_OF_DAY);

        serviceUnderTest.formCandle(stock);

        Mockito.verify(candleRepository).save(any(Candle.class));
    }


}