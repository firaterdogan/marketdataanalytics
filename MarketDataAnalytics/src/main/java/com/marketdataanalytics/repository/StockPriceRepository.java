package com.marketdataanalytics.repository;

import com.marketdataanalytics.model.StockPrice;

import java.time.LocalDateTime;
import java.util.List;


public interface StockPriceRepository extends BaseRepository<StockPrice, Long> {
    List<StockPrice> findAllByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    boolean existsByDataProviderAndDateTimeBetween(String valueOf, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
