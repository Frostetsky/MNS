package com.example.marketplaceservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    @JsonIgnore
    private long id;

    private String name;

    private String description;

    private double price;

    private String imageUrl;
}
