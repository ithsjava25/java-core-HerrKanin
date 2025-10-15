package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable {

    private final LocalDate expirationDate;
    private final BigDecimal weight;

    public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(id, name, category, price);
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (weight.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        }
        this.expirationDate = expirationDate;
        this.weight = weight;
    }
    public LocalDate expirationDate() {
        return expirationDate;
    }
    public Double weight(){
        return weight.doubleValue();
    }
    public BigDecimal calculateShippingCost(){
        return weight.multiply(BigDecimal.valueOf(50));
    }
    public String productDetails(){
        return "Food: " + name() + ", Expires: " + expirationDate;
    }
}
