package com.marketdataanalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarketDataAnalyticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketDataAnalyticsApplication.class, args);
    }
}
