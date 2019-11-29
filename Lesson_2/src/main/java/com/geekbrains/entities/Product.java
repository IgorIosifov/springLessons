package com.geekbrains.entities;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String title;
    private BigDecimal price;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product(Long id, String Title, BigDecimal price) {
        this.id = id;
        this.title = Title;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product:[id = %d; title = '%s'; price = %s]", id, title, price.toString());
    }
}