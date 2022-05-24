package com.example.marketplaceservice.repository;

import com.example.marketplaceservice.model.Product;
import com.example.marketplaceservice.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "from Product p where p.seller = :seller")
    List<Product> findBySeller(@Param("seller") Seller seller);

}
