package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("log-service-route", route ->
                                route.path("/log-service/**")
                                .filters(filter -> filter.stripPrefix(1))
                                .uri("lb://log-service"))
                .route("marketplace-service-route", route ->
                                route.path("/marketplace-service/**")
                                .filters(filter -> filter.stripPrefix(1))
                                .uri("lb://marketplace-service"))
                .build();
    }
}
