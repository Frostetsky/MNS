package com.example.logservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Info {

    private String message;

    private LocalDateTime logTime;
}
