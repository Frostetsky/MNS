package com.example.marketplaceservice.service.product;

import com.example.marketplaceservice.dto.ProductDto;
import com.example.marketplaceservice.model.Product;
import com.example.marketplaceservice.model.Seller;
import com.example.marketplaceservice.repository.ProductRepository;
import com.example.marketplaceservice.service.ProductService;
import com.example.marketplaceservice.util.ProfileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Profile(ProfileType.PROD)
@Service
@Transactional
@ConditionalOnProperty(prefix = "database", name = "in-memory", havingValue = "false")
public class ProductServiceProd implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceProd(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(productEntity -> ProductDto
                        .builder()
                        .name(productEntity.getName())
                        .description(productEntity.getDescription())
                        .imageUrl(productEntity.getImageUrl())
                        .price(productEntity.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> findProductsBySeller(Seller seller) {
        return productRepository.findBySeller(seller)
                .stream()
                .map(productEntity -> ProductDto
                        .builder()
                        .id(productEntity.getId())
                        .name(productEntity.getName())
                        .description(productEntity.getDescription())
                        .imageUrl(productEntity.getImageUrl())
                        .price(productEntity.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
