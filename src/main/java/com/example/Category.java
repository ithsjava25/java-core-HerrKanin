package com.example;

import java.util.HashMap;
import java.util.Map;

public final class Category {
    private final String name;
    private static final Map<String, Category> CACHE = new HashMap<>();

    private Category(String name){
        this.name = name;
    }
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
}
