package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Representerar en matprodukt i lagret
 * Är både perishable och shippable
 */
public class FoodProduct extends Product implements Perishable, Shippable {

    private final LocalDate expirationDate; // Utgångsdatum
    private final BigDecimal weight; // Vikt i kg

    public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(id, name, category, price);

        // Validera pris och vikt
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (weight.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        }
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    // Datum då produkten går ut
    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }

    // Vikt i kg
    @Override
    public Double weight(){
        return weight.doubleValue();
    }

    // Fraktkostnad = vikt * 50
    @Override
    public BigDecimal calculateShippingCost(){
        return weight.multiply(BigDecimal.valueOf(50));
    }

    // Visar detaljer om produkten
    @Override
    public String productDetails(){
        return "Food: " + name() + ", Expires: " + expirationDate;
    }
}
