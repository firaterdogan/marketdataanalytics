package com.marketdataanalytics.listener;

import com.marketdataanalytics.service.StockPriceCommandService;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MarketDataFileListener extends TailerListenerAdapter {

    private static final Logger log = LoggerFactory.getLogger(MarketDataFileListener.class);

    @Autowired
    private StockPriceCommandService stockPriceCommandService;

    private String dataProviderCompanyIdentifier;

    @Override
    public void handle(String s) {
        log.info(dataProviderCompanyIdentifier + " =>+ "+s);
        stockPriceCommandService.savePrice(dataProviderCompanyIdentifier, s);
    }

    public void init(String dataProviderCompanyIdentifier) {
        this.dataProviderCompanyIdentifier=dataProviderCompanyIdentifier;
    }
}
