package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Representerar en produktkategori
 * Klassen är oföränderlig och återanvänder samma instans för samma namn
 */
public final class Category {

    private final String name; // Kategorins namn
    private static final Map<String, Category> CACHE = new HashMap<>();

    // Privat konstruktor, används bara i fabriksmetoden
    private Category(String name){
        this.name = name;
    }

    // Skapar eller återanvänder en Category
    // Validerar, normaliserar och lägger till i cachen vid behov
    public static Category of(String name){
        if (name == null){
            throw new IllegalArgumentException("Category name can't be null");
        }
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()){
            throw new IllegalArgumentException("Category name can't be blank");
        }
        String lower = trimmedName.toLowerCase();
        String normalized = lower.substring(0, 1).toUpperCase() + lower.substring(1);

        CACHE.computeIfAbsent(normalized, Category::new);
        return CACHE.get(normalized);
    }
    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
    // Används bara för snyggare utskrifter i loggar eller felsökning
    @Override
    public String toString() {
        return "Category{" +
                " Name ='" + name + '\'' +
                '}';
    }
}
