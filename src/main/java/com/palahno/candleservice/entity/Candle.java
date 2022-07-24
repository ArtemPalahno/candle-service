package com.palahno.candleservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.palahno.candleservice.dto.Stock;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * @author Artem Palahno
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Candle {

    @JsonIgnore
    private UUID id;

    @EqualsAndHashCode.Exclude
    private Instant openTime;

    @EqualsAndHashCode.Exclude
    private Instant closeTime;

    @EqualsAndHashCode.Exclude
    private BigDecimal minPrice;

    @EqualsAndHashCode.Exclude
    private BigDecimal maxPrice;

    @EqualsAndHashCode.Exclude
    private BigDecimal closePrice;

    @EqualsAndHashCode.Exclude
    private String name;

    public Candle(Stock stock) {
        this.id = UUID.randomUUID();
        this.minPrice = stock.getPrice();
        this.maxPrice = stock.getPrice();
        this.closePrice = stock.getPrice();
        this.openTime = stock.getDateTime();
        this.closeTime = stock.getDateTime();
        this.name = stock.getSymbol();
    }
}
