package com.example.marketplaceservice.service.user;

import com.example.marketplaceservice.model.Seller;
import com.example.marketplaceservice.service.SellerService;
import com.example.marketplaceservice.util.ProfileType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Profile(ProfileType.DEV)
@Service
public class SellerServiceDev implements SellerService {
    @Override
    public boolean isExistWithUsername(String username) {
        return false;
    }

    @Override
    public String save(Seller seller) {
        return null;
    }

    @Override
    public Optional<Seller> findByIdToken(String idToken) {
        return Optional.empty();
    }
}
