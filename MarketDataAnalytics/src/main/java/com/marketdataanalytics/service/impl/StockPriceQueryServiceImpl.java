package com.marketdataanalytics.service.impl;

import com.marketdataanalytics.dto.StockPriceVolatilityDTO;
import com.marketdataanalytics.model.StockPrice;
import com.marketdataanalytics.repository.StockPriceRepository;
import com.marketdataanalytics.service.StockPriceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StockPriceQueryServiceImpl  implements StockPriceQueryService {

    @Autowired
    private StockPriceRepository stockPriceRepository;



    @Override
    public List<StockPriceVolatilityDTO> getStockPriceVolatilities(int interval) {

        List<StockPriceVolatilityDTO> result =new ArrayList<>();

        LocalDateTime endDateTime = LocalDateTime.now();
        LocalDateTime startDateTime = endDateTime.minusSeconds(interval/1000);
        List<StockPrice> stockPrices = this.stockPriceRepository.findAllByDateTimeBetween(startDateTime, endDateTime);

        Map<String, List<StockPrice>> groupByStockCodeMap = stockPrices.stream().collect(Collectors.groupingBy(StockPrice::getStockCode));
        for(Map.Entry<String, List<StockPrice>> entry : groupByStockCodeMap.entrySet()) {
            StockPriceVolatilityDTO stockPriceVolatilityDTO = new StockPriceVolatilityDTO();
            stockPriceVolatilityDTO.setStockCode(entry.getKey());
            int positiveCount = 0;
            int negativeCount = 0;
            for(StockPrice stockPrice : entry.getValue()) {
                if(stockPrice.getPrice().compareTo(stockPrice.getOldPrice())>0) {
                    positiveCount++;
                } else if(stockPrice.getPrice().compareTo(stockPrice.getOldPrice())<0) {
                    negativeCount++;
                }
            }
            stockPriceVolatilityDTO.setPositive(positiveCount);
            stockPriceVolatilityDTO.setNegative(negativeCount);

            result.add(stockPriceVolatilityDTO);
        }
        return result;
    }
}