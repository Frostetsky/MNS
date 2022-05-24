package com.example.marketplaceservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INDEX")
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "SELLER_INDEX", nullable = false)
    private Seller seller;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(imageUrl, product.imageUrl) &&
                Objects.equals(seller, product.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, imageUrl, seller);
    }
}
