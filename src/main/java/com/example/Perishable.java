package com.example;

import java.time.LocalDate;

/**
 * Interface för produkter som har utgångsdatum.
 * Används av FoodProduct
 */
public interface Perishable {

    // Returnerar produktens utgångsdatum
    LocalDate expirationDate();

    // Standardmetod, kollar om produkten har gått ut
    default boolean isExpired() {
        return expirationDate().isBefore(LocalDate.now());
    }
}
