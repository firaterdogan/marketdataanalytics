package com.marketdataanalytics.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockPriceVolatilityDTO {

    private String stockCode;

    private int positive;

    private int negative;
}
