package com.example.logservice.client;

import com.example.logservice.model.Info;
import com.example.logservice.model.LogType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "marketplace-service", url = "/log")
public interface MarketPlaceClient {

    @GetMapping("/getLogs")
    public List<Pair<LogType, Info>> findAllLogs(@RequestParam(name = "secret") String secret);

}
