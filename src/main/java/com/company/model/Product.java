package com.company.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private Integer categoryId;
    private String name;
    private Double price;
    private String image;
    private boolean deleted = false;

    public Product(Integer categoryId, String name, Double price, String image) {
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Product(Integer id) {
        this.id = id;
    }
}
