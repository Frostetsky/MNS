package com.example.marketplaceservice.service.product;

import com.example.marketplaceservice.service.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("DEV")
@Service
public class ProductServiceDev implements ProductService {
}
