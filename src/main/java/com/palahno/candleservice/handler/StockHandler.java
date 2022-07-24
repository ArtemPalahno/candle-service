package com.palahno.candleservice.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.palahno.candleservice.dto.RootMessage;
import com.palahno.candleservice.service.CandleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author Artem Palahno
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class StockHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final CandleService candleService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {
        RootMessage stocks = objectMapper.readValue(message.getPayload(), RootMessage.class);
        log.debug("Stocks received {}", stocks);
        stocks.getData()
                .forEach(candleService::formCandle);
    }
}
