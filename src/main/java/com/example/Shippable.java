package com.example;
import java.math.BigDecimal;

/**
 * Interface för produkter som kan fraktas
 * Implementeras av FoodProduct och ElectronicsProduct
 */

public interface Shippable {

    // Räknar ut fraktkostnaden för produkten
    BigDecimal calculateShippingCost();

    // Returnerar produktens vikt i kg
    Double weight();

}
