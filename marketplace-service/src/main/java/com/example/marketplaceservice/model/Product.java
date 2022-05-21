package com.example.marketplaceservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INDEX")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NAME")
    private String description;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "SELLER_INDEX")
    private Seller seller;
}
