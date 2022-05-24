package com.example.marketplaceservice.service;


import com.example.marketplaceservice.model.Product;
import com.example.marketplaceservice.model.Seller;

import java.util.List;

public interface ProductService {
    List<Product> findBySeller(Seller seller);

    List<Product> findAll();

    void save(Product product);

    void deleteById(Long id);
}
