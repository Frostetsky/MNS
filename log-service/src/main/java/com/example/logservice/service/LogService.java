package com.example.logservice.service;

import com.example.logservice.client.MarketPlaceClient;
import com.example.logservice.model.Info;
import com.example.logservice.model.Log;
import com.example.logservice.model.LogType;
import com.example.logservice.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LogService {

    @Value("${rest.secret}")
    private String secret;

    private static final long ONE_MINUTE = 1000 * 60;

    private final MarketPlaceClient marketPlaceClient;

    private final LogRepository logRepository;

    @Autowired
    public LogService(MarketPlaceClient marketPlaceClient, LogRepository logRepository) {
        this.marketPlaceClient = marketPlaceClient;
        this.logRepository = logRepository;
    }

    @Scheduled(initialDelay = ONE_MINUTE, cron = "*/30 * * * * *")
    public void getLogsAndSave() {
        List<Pair<LogType, Info>> logs = marketPlaceClient.findAllLogs(secret);

        if (logs.isEmpty()) {
            return;
        }

        Map<LogType, List<Info>> logsMaps = new HashMap<>();

        logs.forEach(pair -> {
            LogType logType = pair.getFirst();
            Info info = pair.getSecond();
            logsMaps.putIfAbsent(logType, new ArrayList<>());
            logsMaps.computeIfPresent(logType, (k, v) -> {
                v.add(info);
                return v;
            });
        });

        save(logsMaps);
    }

    private void save(Map<LogType, List<Info>> logs) {
        List<Log> listLogs = logs.entrySet()
                .stream()
                .map(log -> Log.builder()
                        .logType(log.getKey())
                        .infos(log.getValue())
                        .build())
                .collect(Collectors.toList());

        logRepository.saveAll(listLogs);
    }
}
