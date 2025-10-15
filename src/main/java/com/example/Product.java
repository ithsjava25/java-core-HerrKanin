package com.example;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public abstract class Product {
    private final UUID id;
    private final String name;
    private final Category category;
    private BigDecimal price;

    protected Product(UUID id, String name, Category category, BigDecimal price) {
        Objects.requireNonNull(id, "id cannot be null");
        this.id = id;
        Objects.requireNonNull(name, "name cannot be null");
        this.name = name;
        Objects.requireNonNull(category, "category cannot be null");
        this.category = category;
        Objects.requireNonNull(price, "price cannot be null");
        this.price = price;
    }
    public UUID uuid() {
        return id;
    }
    public String name() {
        return name;
    }
    public Category category() {
        return category;
    }
    public BigDecimal price() {
        return price;
    }

    public void price(BigDecimal newPrice) {
        this.price = Objects.requireNonNull(newPrice, "price cannot be null");
    }
    public abstract String productDetails();
}
