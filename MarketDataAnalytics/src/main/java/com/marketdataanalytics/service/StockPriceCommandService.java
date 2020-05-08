package com.marketdataanalytics.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface StockPriceCommandService {
    void savePrice(String dataProvider, String line);
    void savePrice(String dataProvider, LocalDateTime dateTime, String stockCode, BigDecimal oldPrice, BigDecimal price);
}
