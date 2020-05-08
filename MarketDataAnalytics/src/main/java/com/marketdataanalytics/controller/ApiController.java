package com.marketdataanalytics.controller;

import com.marketdataanalytics.controller.response.ResponseOfList;
import com.marketdataanalytics.dto.StockPriceVolatilityDTO;
import com.marketdataanalytics.service.StockPriceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private StockPriceQueryService stockPriceQueryService;

    @GetMapping("/volatilities/{interval}")
    public ResponseOfList<StockPriceVolatilityDTO> getStockPriceVolatilities(@PathVariable("interval") int interval) {
        return new ResponseOfList<>(this.stockPriceQueryService.getStockPriceVolatilities(interval));
    }
}
