package com.example.marketplaceservice.service;

import com.example.marketplaceservice.model.Seller;
import java.util.Optional;

public interface SellerService {

    boolean isExistWithUsername(String username);

    String save(Seller seller);

    Optional<Seller> findByIdToken(String idToken);
}
