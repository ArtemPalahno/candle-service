package com.palahno.candleservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author Artem Palahno
 */
@Data
public class Stock {
    @JsonProperty("s")
    private String symbol;
    @JsonProperty("p")
    private BigDecimal price;
    @JsonProperty("t")
    private Instant dateTime;
    @JsonProperty("v")
    private String version;
}
