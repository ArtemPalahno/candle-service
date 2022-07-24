package com.palahno.candleservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

/**
 * @author Artem Palahno
 */
@Configuration
public class WebSocketConfiguration {

    @Bean
    public WebSocketConnectionManager wsConnectionManager(WebSocketHandler webSocketHandler,
                                                          @Value("${stock.websocket.uri}") String websocketUri) {

        //Generates a web socket connection
        WebSocketConnectionManager manager = new WebSocketConnectionManager(
                new StandardWebSocketClient(),
                webSocketHandler,
                websocketUri);

        //Will connect as soon as possible
        manager.setAutoStartup(true);

        return manager;
    }



}
