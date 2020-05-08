package com.marketdataanalytics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class MarketDataAnalyticsThreadConfig {

    @Bean(name = "marketdataanalyticsAsyncTaskExecutor")
    public TaskExecutor marketdataanalyticsAsyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(600);
        executor.setThreadNamePrefix("marketdataanalyticsAsyncTaskExecutor_thread");
        executor.initialize();
        return executor;
    }
}
