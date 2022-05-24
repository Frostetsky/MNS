package com.example.marketplaceservice.controller;

import com.example.marketplaceservice.model.Product;
import com.example.marketplaceservice.repository.ProductRepository;
import com.example.marketplaceservice.service.ProductService;
import com.example.marketplaceservice.service.product.ProductServiceProd;
import net.bytebuddy.description.annotation.AnnotationValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/public/market")
@RefreshScope
public class PublicMarketController {


    @Value("${pages.size}")
    private String size;

    private final ProductService productService;

    @Autowired
    public PublicMarketController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProducts")
    public List<Product> getProductsByCriteria(@RequestParam("page") Integer page,
                                               @RequestParam(value = "sorted", required = false) String sortedCriteria,
                                               @RequestParam(value = "criteria", required = false, defaultValue = "price") String field) {

        Sort sort;

        switch (sortedCriteria) {
            case "desc":
                sort = Sort.by(field).descending();
                break;
            case "asc":
                sort = Sort.by(field).ascending();
                break;
            default:
                sort = Sort.unsorted();
        }

        PageRequest pageRequest = PageRequest.of(page, Integer.parseInt(size), sort);

        return productService.getProducts(pageRequest).getContent();
    }
}
