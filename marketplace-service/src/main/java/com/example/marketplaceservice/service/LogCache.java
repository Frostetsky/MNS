package com.example.marketplaceservice.service;

import com.example.marketplaceservice.model.LogType;
import com.example.marketplaceservice.model.Info;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogCache {

    private final List<Pair<LogType, Info>> storeCache = new ArrayList<>();

    public void saveInfo(LogType logType, Info info) {
        Pair<LogType, Info> log = Pair.of(logType, info);
        storeCache.add(log);
    }

    public List<Pair<LogType, Info>> findAll() {
        return storeCache;
    }

    public void clearCache() {
        storeCache.clear();
    }
}
