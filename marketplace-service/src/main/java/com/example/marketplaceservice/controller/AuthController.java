package com.example.marketplaceservice.controller;

import com.example.marketplaceservice.dto.SignUpDto;
import com.example.marketplaceservice.model.Info;
import com.example.marketplaceservice.model.LogType;
import com.example.marketplaceservice.model.Seller;
import com.example.marketplaceservice.service.LogCache;
import com.example.marketplaceservice.service.SellerService;
import com.example.marketplaceservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final SellerService sellerService;

    private final LogCache logCache;

    @Autowired
    public AuthController(SellerService sellerService, LogCache logCache) {
        this.logCache = logCache;
        this.sellerService = sellerService;
    }

    @PostMapping("/authorization")
    public ResponseEntity<String> signUpAndAuthorization(@Valid @RequestBody SignUpDto signUpDto,
                                                         BindingResult bindingResult,
                                                         HttpServletResponse httpServletResponse) {

        boolean usernameIsExist = sellerService.isExistWithUsername(signUpDto.getUsername());

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            logCache.saveInfo(LogType.WARN, Info.builder()
                    .message(String.format("Попытка зарегистрироваться с невалидными данными. Сообщение об ошибке - %s", errorMessage))
                    .build());
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        } else if (usernameIsExist) {
            logCache.saveInfo(LogType.WARN, Info.builder()
                    .message(String.format("Попытка зарегистрироваться с username - %s. Данный username занят.", signUpDto.getUsername()))
                    .build());
            return new ResponseEntity<>(Constants.USERNAME_IS_EXIST, HttpStatus.BAD_REQUEST);
        } else {
            Seller seller = new Seller();
            seller.setPassword(signUpDto.getPassword());
            seller.setUsername(signUpDto.getUsername());
            seller.setRegistrationDate(LocalDateTime.now().withNano(0));
            String idToken = sellerService.save(seller);
            Cookie cookie = new Cookie("idToken", idToken);
            httpServletResponse.addCookie(cookie);
            logCache.saveInfo(LogType.SUCCESS, Info.builder()
                    .message(String.format("Пользователь с username - %s зарегистрирован.", signUpDto.getUsername()))
                    .build());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
