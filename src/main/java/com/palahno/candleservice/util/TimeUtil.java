package com.palahno.candleservice.util;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

/**
 * @author Artem Palahno
 */
@UtilityClass
public class TimeUtil {

    public int compare(Instant instant1, Instant instant2, ChronoField unit, int amount) {
        return Integer.compare(instant1.atZone(ZoneOffset.UTC).get(unit) - amount, instant2.atZone(ZoneOffset.UTC).get(unit));
    }

}
