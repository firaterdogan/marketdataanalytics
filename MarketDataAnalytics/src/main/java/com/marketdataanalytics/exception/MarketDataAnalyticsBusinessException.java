package com.marketdataanalytics.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarketDataAnalyticsBusinessException extends RuntimeException {

    private List<String> parameters = new ArrayList<>();

    public MarketDataAnalyticsBusinessException(String message) {
        super(message);
    }

    public MarketDataAnalyticsBusinessException(String message, String... parameters) {
        super(message);
        this.parameters = Arrays.asList(parameters);
    }
}
