package com.example.marketplaceservice.service.user;

import com.example.marketplaceservice.model.Seller;
import com.example.marketplaceservice.repository.SellerRepository;
import com.example.marketplaceservice.service.SellerService;
import com.example.marketplaceservice.util.JwtVerifier;
import com.example.marketplaceservice.util.ProfileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Profile(ProfileType.PROD)
@Service
@Transactional
@ConditionalOnProperty(prefix = "database", name = "in-memory", havingValue = "false")
public class SellerServiceProd implements SellerService {

    private final SellerRepository sellerRepository;

    private final JwtVerifier jwtVerifier;

    @Autowired
    public SellerServiceProd(SellerRepository sellerRepository, JwtVerifier jwtVerifier) {
        this.jwtVerifier = jwtVerifier;
        this.sellerRepository = sellerRepository;
    }

    public boolean isExistWithUsername(String username) {
        return sellerRepository.existsByUsername(username);
    }

    @Override
    public String save(Seller seller) {
        String idToken = jwtVerifier.createToken(seller);
        seller.setIdToken(idToken);
        sellerRepository.save(seller);
        return idToken;
    }

    @Override
    public Optional<Seller> findByIdToken(String idToken) {
        return sellerRepository.findByIdToken(idToken);
    }
}
