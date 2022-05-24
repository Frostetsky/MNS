package com.example.marketplaceservice.service.product;

import com.example.marketplaceservice.model.Product;
import com.example.marketplaceservice.model.Seller;
import com.example.marketplaceservice.repository.ProductRepository;
import com.example.marketplaceservice.service.ProductService;
import com.example.marketplaceservice.util.ProfileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile(ProfileType.PROD)
@Service
public class ProductServiceProd implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceProd(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Product> findBySeller(Seller seller) {
        return productRepository.findBySeller(seller);
    }
}
