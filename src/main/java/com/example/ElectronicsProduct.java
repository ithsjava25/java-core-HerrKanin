package com.example;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Representerar en elektronisk produkt i lagret
 * Är shippable men INTE perishable
 */

public class ElectronicsProduct extends Product implements Shippable{

    private final int warrantyMonths; // Garanti i antal månader
    private final BigDecimal weight; // Vikt i kg

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(id, name, category, price);

        // Kollar så att garantin inte är negativ
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        }
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    // Returnerar vikten i kg
    @Override
    public Double weight(){
        return weight.doubleValue();
    }

    // Räknar ut fraktkostnaden. Startpris 79 plus 49 extra om vikten är över 5 kg
    @Override
    public BigDecimal calculateShippingCost(){
        BigDecimal basePrice = BigDecimal.valueOf(79);
        if (weight.doubleValue() > 5.0){
            basePrice = basePrice.add(BigDecimal.valueOf(49));
        }
        return basePrice;
    }

    // Visar information om produkten
    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }

}
