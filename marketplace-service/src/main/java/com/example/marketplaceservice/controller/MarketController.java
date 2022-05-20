package com.example.marketplaceservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/market")
public class MarketController {

    @GetMapping("/health")
    public String health() {
        return "UP";
    }

}
