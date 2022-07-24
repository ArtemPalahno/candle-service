package com.palahno.candleservice.configuration;

import com.palahno.candleservice.dto.CandleConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_DAY;

/**
 * @author Artem Palahno
 */
@Configuration
public class CandleConfiguration {

    private final Map<String, ChronoField> candleUnits = new HashMap<>() {{
        put("minutes", MINUTE_OF_DAY);
        put("hours", HOUR_OF_DAY);
    }};


    @Bean
    public CandleConfig candleConfig(@Value("${candle.unit.count}") Integer candleUnitCount, @Value("${candle.unit.type}") String candleUnitType) {
        return new CandleConfig(candleUnitCount, candleUnits.get(candleUnitType));
    }

}
