package com.example.marketplaceservice.controller;

import com.example.marketplaceservice.dto.SignUpDto;
import com.example.marketplaceservice.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/auth")
public class AuthController {

    private final SellerService sellerService;

    @Autowired
    public AuthController(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @PostMapping("/authorization")
    public String signUpAndAuthorization(@Valid @RequestBody SignUpDto signUpDto, BindingResult bindingResult) {

        return null;
    }
}
