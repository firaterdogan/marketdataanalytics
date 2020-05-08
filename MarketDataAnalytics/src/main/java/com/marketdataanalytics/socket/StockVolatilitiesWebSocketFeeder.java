package com.marketdataanalytics.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketdataanalytics.dto.StockPriceVolatilityDTO;
import com.marketdataanalytics.repository.WebSocketSessionRepository;
import com.marketdataanalytics.service.StockPriceQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Component
public class StockVolatilitiesWebSocketFeeder {

    private static final Logger log = LoggerFactory.getLogger(StockVolatilitiesWebSocketHandler.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private StockPriceQueryService stockPriceQueryService;

    @Autowired
    private WebSocketSessionRepository webSocketSessionHolder;

    @Value("${marketdataanalytics.stockvolatilities.feeder.interval}")
    private int interval;

    @Scheduled(fixedDelayString = "${marketdataanalytics.stockvolatilities.feeder.delay}")
    private void checkDataProvider() {

        LocalDateTime endDateTime = LocalDateTime.now();
        LocalDateTime startDateTime = endDateTime.minusSeconds(this.interval);
        List<StockPriceVolatilityDTO> stockPriceVolatilityDTOS = this.stockPriceQueryService.getStockPriceVolatilities(this.interval);
        Collection<WebSocketSession> webSocketSessions = this.webSocketSessionHolder.getWebSocketSessions();
        try {
            for (WebSocketSession webSocketSession : webSocketSessions) {
                webSocketSession.sendMessage(new TextMessage(mapper.writeValueAsString(stockPriceVolatilityDTOS)));
            }
        } catch (Exception e) {
            log.error("webSocketSession.sendMessage exception", e);
        }
    }
}
