package com.palahno.candleservice.util;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoField.MINUTE_OF_DAY;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Artem Palahno
 */
class TimeUtilTest {

    @Test
    void shouldCompareInstants() {
        Instant instant1 = Instant.now();
        Instant instant2 = Instant.now().plus(2, ChronoUnit.MINUTES);

        int actualResult = TimeUtil.compare(instant1, instant2, MINUTE_OF_DAY, 1);

        assertTrue(actualResult < 0);
    }

}