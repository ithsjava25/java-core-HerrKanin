package com.example;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Abstrakt basklass för alla produkter i lagret
 * Innehåller gemensamma fält och metoder som namn, pris, kategori & id
 */
public abstract class Product {
    private final UUID id; // Unikt ID för produkten
    private final String name; // Produktens namn
    private final Category category; // Produktens kategori
    private BigDecimal price; // Aktuellt pris

    // Protected konstruktor, används av underklasser
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

    // Getters (id, name, category, price)
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
    // Sätter nytt pris
    public void price(BigDecimal newPrice) {
        this.price = Objects.requireNonNull(newPrice, "price cannot be null");
    }
    // Måste implementeras av underklasser
    public abstract String productDetails();
}
