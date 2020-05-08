package com.marketdataanalytics.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class holds the websocket session map and
 * provides put/get/delete functions for the map
 * Sessions are hold in ConcurrentHashMap so methods are thread-safe.
 *
 * @author  Firat Erdogan
 * @version 1.0
 */
@Component
public class WebSocketSessionRepositoryImpl implements WebSocketSessionRepository{

    private static final Logger log = LoggerFactory.getLogger(WebSocketSessionRepository.class);

    private Map<String, WebSocketSession> webSocketSessions = new ConcurrentHashMap<>();

    /**
     * This method used to add/replace websocket session.
     * @param webSocketSession websocket session.
     * @see WebSocketSession
     */
    @Override
    public void putWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSessions.put(webSocketSession.getId(), webSocketSession);
        log.debug("WebSocket Session added : " + webSocketSession);
    }

    /**
     * This method used to remove websocket session by sessionId
     * @param sessionId websocket session id.
     */
    @Override
    public void removeWebSocketSession(String sessionId) {
        this.webSocketSessions.remove(sessionId);
        log.debug("WebSocket Session removed : " + sessionId);
    }

    /**
     * This method used to remove the websocket session
     * @param session websocket session.
     * @see WebSocketSession
     */
    @Override
    public void removeWebSocketSession(WebSocketSession session) {
        log.debug("WebSocket Session is removed : " + session);
        this.webSocketSessions.remove(session.getId());
    }

    /**
     * Returns all websocket sessions
     * @return collection of websocket sessions
     * @see WebSocketSession
     */
    @Override
    public Collection<WebSocketSession> getWebSocketSessions() {
        log.debug("WebSocket Sessions is fetching..");
        return this.webSocketSessions.values();
    }
}