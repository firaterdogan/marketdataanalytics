package com.marketdataanalytics.service.impl;

import com.marketdataanalytics.model.StockPrice;
import com.marketdataanalytics.repository.StockPriceRepository;
import com.marketdataanalytics.service.StockPriceCommandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class StockPriceCommandServiceImpl implements StockPriceCommandService {

    @Autowired
    private StockPriceRepository stockPriceRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void savePrice(String dataProvider, String line) {
        String[] parts =  StringUtils.split(line,"|");
        LocalDateTime dateTime = LocalDateTime.parse(parts[0],formatter);
        String stockCode = parts[1];
        BigDecimal oldPrice = new BigDecimal(parts[2]);
        BigDecimal price = new BigDecimal(parts[3]);
        this.savePrice(dataProvider,dateTime, stockCode,oldPrice,price);
    }

    @Override
    public void savePrice(String dataProvider, LocalDateTime dateTime, String stockCode, BigDecimal oldPrice, BigDecimal price) {
        this.stockPriceRepository.save(new StockPrice(dataProvider,dateTime, stockCode,oldPrice,price));
    }
}
