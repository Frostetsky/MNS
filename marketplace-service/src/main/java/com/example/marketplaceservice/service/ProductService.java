package com.example.marketplaceservice.service;


import com.example.marketplaceservice.model.Product;
import com.example.marketplaceservice.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findBySeller(Seller seller);

    List<Product> findAll();

    void save(Product product);

    void deleteById(Long id);

    Page<Product> getProducts(Pageable pageable);
}
