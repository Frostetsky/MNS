package com.example.marketplaceservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.marketplaceservice.model.Seller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RefreshScope
public class JwtVerifier {


    @Value("${jwt.token.secret}")
    private String SECRET;

    private final long EXP = LocalDateTime.now()
            .plus(30, ChronoUnit.DAYS)
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli();

    public String createToken(Seller seller) {
        String usernameAndPassword = seller.getPassword().concat(seller.getUsername());
        return JWT.create()
                .withSubject(usernameAndPassword)
                .withExpiresAt(new Date(EXP))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }
}
