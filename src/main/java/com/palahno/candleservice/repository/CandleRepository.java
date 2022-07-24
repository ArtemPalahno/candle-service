package com.palahno.candleservice.repository;

import com.palahno.candleservice.entity.Candle;

import java.util.LinkedList;

/**
 * @author Artem Palahno
 */
public interface CandleRepository extends IRepository<Candle> {
    LinkedList<Candle> findAllByName(String name);
}
