package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable{

    private final int warrantyMonths;
    private final BigDecimal weight;

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(id, name, category, price);
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        }
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }
    public Double weight(){
        return weight.doubleValue();
    }
    public BigDecimal calculateShippingCost(){
        BigDecimal basePrice = BigDecimal.valueOf(79);
        if (weight.doubleValue() > 5.0){
            basePrice = basePrice.add(BigDecimal.valueOf(49));
        }
        return basePrice;
    }
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }

}
