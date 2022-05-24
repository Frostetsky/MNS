package com.example.marketplaceservice.controller;

import com.example.marketplaceservice.model.Info;
import com.example.marketplaceservice.model.LogType;
import com.example.marketplaceservice.service.LogCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/log")
@RefreshScope
public class LogController {

    @Value("${rest.secret}")
    private String secret;

    private final LogCache logCache;

    @Autowired
    public LogController(LogCache logCache) {
        this.logCache = logCache;
    }

    @GetMapping("/getLogs")
    public List<Pair<LogType, Info>> findAllLogs(@RequestParam(name = "secret") String secret) {

        if (Objects.equals(secret, this.secret)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверный идентифицирующий параметр или же он отсутствует вовсе.");
        }

        List<Pair<LogType, Info>> result = logCache.findAll();
        logCache.clearCache();
        return result;
    }
}
