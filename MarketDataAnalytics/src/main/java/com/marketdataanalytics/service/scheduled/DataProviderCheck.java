package com.marketdataanalytics.service.scheduled;

import com.marketdataanalytics.repository.StockPriceRepository;
import com.marketdataanalytics.service.MailCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataProviderCheck {

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Autowired
    private MailCommandService mailCommandService;

    @Value("${marketdataanalytics.dataprovidercheck.check.interval}")
    private int interval;

    @Value("${marketdataanalytics.dataprovidercheck.check.to}")
    private String to;

    @Scheduled(fixedDelayString = "${marketdataanalytics.dataprovidercheck.check.delay}")
    private void checkDataProvider() {
        LocalDateTime endDateTime = LocalDateTime.now();
        LocalDateTime startDateTime = endDateTime.minusSeconds(this.interval);
        for(int i = 1; i<4;i++){
            if(!this.stockPriceRepository.existsByDataProviderAndDateTimeBetween(String.valueOf(i),startDateTime, endDateTime)) {
                mailCommandService.sendMail(new String[]{to}, "There is no new data from Data Provider: "+ i, "There is no new data from Data Provider: "+ i);
            }
        }
    }
}
