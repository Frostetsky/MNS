package com.example.marketplaceservice.service;


import com.example.marketplaceservice.dto.ProductDto;
import com.example.marketplaceservice.model.Product;
import com.example.marketplaceservice.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductDto> findProductsBySeller(Seller seller);

    void save(Product product);

    void deleteById(Long id);

    List<ProductDto> getProducts(Pageable pageable);
}
