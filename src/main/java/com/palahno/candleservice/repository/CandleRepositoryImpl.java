package com.palahno.candleservice.repository;

import com.palahno.candleservice.entity.Candle;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Artem Palahno
 */
@Component
public class CandleRepositoryImpl implements CandleRepository {

    private final Map<String, LinkedList<Candle>> candleStore = new ConcurrentHashMap<>();

    @Override
    public void save(Candle candle) {
        LinkedList<Candle> candles = candleStore.get(candle.getName());
        if (candles != null) {
            int indexOfCandle = candles.indexOf(candle);
            if (indexOfCandle != -1) {
                candles.set(indexOfCandle, candle);
            } else {
                candles.addLast(candle);
            }
        } else {
            LinkedList<Candle> newList = new LinkedList<>();
            newList.add(candle);
            candleStore.put(candle.getName(), newList);
        }
    }

    @Override
    public LinkedList<Candle> findAllByName(String name) {
        LinkedList<Candle> candles = candleStore.get(name);
        if (candles == null) {
            return new LinkedList<>();
        }
        return candles;
    }
}
