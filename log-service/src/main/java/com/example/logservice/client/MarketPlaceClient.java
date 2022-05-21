package com.example.logservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "marketplace-service")
public interface MarketPlaceClient {
}
