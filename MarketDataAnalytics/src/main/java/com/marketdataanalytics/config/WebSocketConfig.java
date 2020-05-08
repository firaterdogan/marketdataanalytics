package com.marketdataanalytics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;


/**
 * Implementation of WebSocketConfigurer
 *
 * @author  Firat Erdogan
 * @version 1.0
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSocketHandler stockVolatilitiesWebSocketHandler;

    /**
     * This method used to remove the websocket session
     * @param registry websocket handler registry.
     * @see WebSocketHandler
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(stockVolatilitiesWebSocketHandler, "/stockvolatilities");
    }

}