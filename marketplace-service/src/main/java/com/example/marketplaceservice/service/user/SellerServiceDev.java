package com.example.marketplaceservice.service.user;

import com.example.marketplaceservice.model.Product;
import com.example.marketplaceservice.model.Seller;
import com.example.marketplaceservice.service.SellerService;
import com.example.marketplaceservice.util.ProfileType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Profile(ProfileType.DEV)
@Service
@ConditionalOnProperty(prefix = "database", name = "in-memory", havingValue = "true")
public class SellerServiceDev implements SellerService {

    @Value("${store.size}")
    private String size;

    private final Map<Long, Product> store = new ConcurrentHashMap<>(Integer.parseInt(size));

    @Override
    public boolean isExistWithUsername(String username) {
        return false;
    }

    @Override
    public String save(Seller seller) {
        return null;
    }

    @Override
    public Optional<Seller> findByIdToken(String idToken) {
        return Optional.empty();
    }
}
