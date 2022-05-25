package com.example.marketplaceservice.service.product;

import com.example.marketplaceservice.dto.ProductDto;
import com.example.marketplaceservice.model.Product;
import com.example.marketplaceservice.model.Seller;
import com.example.marketplaceservice.service.ProductService;
import com.example.marketplaceservice.util.ProfileType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Profile(ProfileType.DEV)
@Service
@ConditionalOnProperty(prefix = "database", name = "in-memory", havingValue = "true")
@RefreshScope
public class ProductServiceDev implements ProductService {

    @Value("${store.size}")
    private String size;

    private final Map<Long, Product> store = new ConcurrentHashMap<>(Integer.parseInt(size));

    @Override
    public List<ProductDto> findProductsBySeller(Seller seller) {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<ProductDto> getProducts(Pageable pageable) {
        return null;
    }
}
