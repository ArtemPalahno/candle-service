package com.palahno.candleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@SpringBootApplication
public class CandleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandleServiceApplication.class, args);
	}

}
