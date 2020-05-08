package com.marketdataanalytics.service;

import com.marketdataanalytics.dto.StockPriceVolatilityDTO;

import java.util.List;

public interface StockPriceQueryService {
    List<StockPriceVolatilityDTO> getStockPriceVolatilities(int interval);
}
