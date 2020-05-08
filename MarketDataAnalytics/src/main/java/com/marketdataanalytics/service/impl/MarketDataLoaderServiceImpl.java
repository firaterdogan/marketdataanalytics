package com.marketdataanalytics.service.impl;


import com.marketdataanalytics.listener.MarketDataFileListener;
import com.marketdataanalytics.service.MarketDataLoaderService;
import org.apache.commons.io.input.Tailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
@Transactional
public class MarketDataLoaderServiceImpl implements MarketDataLoaderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketDataLoaderServiceImpl.class);

    @Value("${marketdataanalytics.marketdata.root.folder}")
    private String marketDataRootFolder;

    @Value("${marketdataanalytics.dataloader.tail.delay:100}")
    private int tailDelayMillis;

    @Autowired
    public MarketDataFileListener marketDataFileListener1;

    @Autowired
    public MarketDataFileListener marketDataFileListener2;

    @Autowired
    public MarketDataFileListener marketDataFileListener3;

    @PostConstruct
    public void dataProviderSimulator() {

        this.marketDataFileListener1.init("1");
        this.marketDataFileListener2.init("2");
        this.marketDataFileListener3.init("3");

        String fileName1 = marketDataRootFolder + "/1.txt";
        String fileName2 = marketDataRootFolder + "/2.txt";
        String fileName3 = marketDataRootFolder + "/3.txt";

        Tailer tailer1 = new Tailer(new File(fileName1), marketDataFileListener1, tailDelayMillis);
        Thread thread1 = new Thread(tailer1);
        thread1.start();

        Tailer tailer2 = new Tailer(new File(fileName2), marketDataFileListener2, tailDelayMillis);
        Thread thread2 = new Thread(tailer2);
        thread2.start();

        Tailer tailer3 = new Tailer(new File(fileName3), marketDataFileListener3, tailDelayMillis);
        Thread thread3 = new Thread(tailer3);
        thread3.start();
    }
}
