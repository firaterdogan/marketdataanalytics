package com.marketdataanalytics.socket;

import com.marketdataanalytics.repository.WebSocketSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class StockVolatilitiesWebSocketHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(StockVolatilitiesWebSocketHandler.class);

    @Autowired
    private WebSocketSessionRepository webSocketSessionHolder;

    /**
     * remove websocket session
     *
     * @param session websocket session
     * @param status websocket status
     *
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        try {
            if (!status.equals(CloseStatus.NORMAL)) {
                session.close();
            }
        } catch (IOException e) {
            log.error("Cannot close session on afterConnectionClosed", e);
        }
        this.webSocketSessionHolder.removeWebSocketSession(session);
    }

    /**
     * starts init messages
     *
     * @param session websocket session
     *
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.debug("afterConnectionEstablishedfor the session: ", session);
        this.webSocketSessionHolder.putWebSocketSession(session);
    }

    /**
     * remove websocket session
     *
     * @param session websocket session
     * @param exception exception
     *
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.debug("error has occured for the session: ", session);
        try {
            session.close();
        } catch (IOException e) {
            log.error("Cannot close session on handleTransportError ", e);
        }
        this.webSocketSessionHolder.removeWebSocketSession(session);
    }
}
