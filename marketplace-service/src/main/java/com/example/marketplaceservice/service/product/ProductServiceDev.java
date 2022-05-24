package com.example.marketplaceservice.service.product;

import com.example.marketplaceservice.model.Product;
import com.example.marketplaceservice.model.Seller;
import com.example.marketplaceservice.service.ProductService;
import com.example.marketplaceservice.util.ProfileType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile(ProfileType.DEV)
@Service
public class ProductServiceDev implements ProductService {
    @Override
    public List<Product> findBySeller(Seller seller) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
