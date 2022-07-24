package com.palahno.candleservice.dto;

import lombok.AllArgsConstructor;

import lombok.Getter;

import java.time.temporal.ChronoField;

/**
 * @author Artem Palahno
 */
@AllArgsConstructor
@Getter
public class CandleConfig {
    private int count;
    private ChronoField type;
}
