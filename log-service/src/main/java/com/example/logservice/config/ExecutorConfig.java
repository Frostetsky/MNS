package com.example.logservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RefreshScope
public class ExecutorConfig {

    @Value("${executor.core-pool-size}")
    private String corePoolSize;

    @Value("${executor.max-pool-size}")
    private String maxPoolSize;

    @Bean(name = "task-executor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(Integer.parseInt(corePoolSize));
        threadPoolTaskExecutor.setMaxPoolSize(Integer.parseInt(maxPoolSize));
        return threadPoolTaskExecutor;
    }
}
