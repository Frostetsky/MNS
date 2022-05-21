package com.example.marketplaceservice.service.user;

import com.example.marketplaceservice.service.SellerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("DEV")
@Service
public class SellerServiceDev implements SellerService {
}
