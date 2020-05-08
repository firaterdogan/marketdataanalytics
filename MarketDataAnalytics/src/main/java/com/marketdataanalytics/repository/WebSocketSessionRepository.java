package com.marketdataanalytics.repository;

import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;

/**
 * @author  Firat Erdogan
 * @version 1.0
 */
public interface WebSocketSessionRepository {

    void putWebSocketSession(WebSocketSession webSocketSession);
    void removeWebSocketSession(String sessionId);
    void removeWebSocketSession(WebSocketSession session);
    Collection<WebSocketSession> getWebSocketSessions();

}