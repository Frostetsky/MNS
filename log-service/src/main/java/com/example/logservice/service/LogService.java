package com.example.logservice.service;

import com.example.logservice.client.MarketPlaceClient;
import com.example.logservice.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final MarketPlaceClient marketPlaceClient;

    private final LogRepository logRepository;

    @Autowired
    public LogService(MarketPlaceClient marketPlaceClient, LogRepository logRepository) {
        this.marketPlaceClient = marketPlaceClient;
        this.logRepository = logRepository;
    }
}
