package com.example.marketplaceservice.controller;

import com.example.marketplaceservice.exception.AuthorizationException;
import com.example.marketplaceservice.model.Info;
import com.example.marketplaceservice.model.LogType;
import com.example.marketplaceservice.model.Product;
import com.example.marketplaceservice.model.Seller;
import com.example.marketplaceservice.service.LogCache;
import com.example.marketplaceservice.service.ProductService;
import com.example.marketplaceservice.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


@RestController
@RequestMapping("/private/product")
public class PrivateMarketController {

    private final SellerService sellerService;

    private final ProductService productService;

    private final LogCache logCache;

    @Autowired
    public PrivateMarketController(SellerService sellerService, LogCache logCache, ProductService productService) {
        this.logCache = logCache;
        this.productService = productService;
        this.sellerService = sellerService;
    }

    @GetMapping("/findProducts")
    public List<Product> findAll(@CookieValue(name = "idToken") String token) throws AuthorizationException {

        var seller = getUserByToken(token);
        return findBySeller(seller);
    }

    @PostMapping("/saveProduct")
    public String addProduct(@CookieValue(name = "idToken") String token,
                             @RequestBody Product product) throws AuthorizationException {

        var seller = getUserByToken(token);

        product.setSeller(seller);

        productService.save(product);

        return "Товар успешно добавлен.";
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProductById(@CookieValue(name = "idToken") String token,
                                    @PathVariable(name = "id") Long id) throws AuthorizationException {

        var seller = getUserByToken(token);
        var products = findBySeller(seller);

        var status = new AtomicBoolean(false);

        products.stream()
                .filter(product -> product.getId() == id)
                .forEach(product -> {
                    productService.deleteById(id);
                    status.set(true);
                });

        return status.get()
                ? "Удаление прошло успешно."
                : "Продукт с id = " + id + " не найден.";
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<String> requestConflict(AuthorizationException authorizationException) {
        return new ResponseEntity<>(authorizationException.getMessage(), HttpStatus.FORBIDDEN);
    }

    private Seller getUserByToken(String token) throws AuthorizationException {
       var seller = sellerService.findByIdToken(token);
       if (seller.isPresent()) {
           return seller.get();
       } else {
           logCache.saveInfo(LogType.ERROR, Info.builder()
                           .message(String.format("Доступ пользователю с токеном - %s был запрещен", token))
                           .build());
           throw new AuthorizationException("Ошибка доступа, авторизуйтесь повторно.");
       }
    }

    private List<Product> findBySeller(Seller seller) {
        return productService.findBySeller(seller);
    }
}
