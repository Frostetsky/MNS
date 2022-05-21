package com.example.marketplaceservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Info {

    private String message;

    private final LocalDateTime logTime = LocalDateTime.now().withNano(0);
}
