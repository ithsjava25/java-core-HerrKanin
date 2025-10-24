package com.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Representerar ett lager som innehåller produkter
 */
public final class Warehouse {

    // Håller alla unika lager baserat på namn
    private static final Map<String, Warehouse> INSTANCE = new HashMap<>();

    private final String name; // Lagrets namn
    private final Map<UUID, Product> products = new HashMap<>(); // Lagrade produkter
    private final List<Product> changedProducts = new ArrayList<>(); // Ändrade produkter

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance(String name){
        return INSTANCE.computeIfAbsent(name, Warehouse::new);
    }

    // Lägger till en produkt i lagret
    public void addProduct(Product product){
        if (product == null){
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (products.containsKey(product.uuid())) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        products.put(product.uuid(), product);
    }

    // Kollar om lagret är tomt
    public boolean isEmpty(){
        return products.isEmpty();
    }

    // Tömmer hela lagret
    public void clearProducts() {
        products.clear();
        changedProducts.clear();
    }

    // Returnerar en oföränderlig lista av alla produkter
    public List<Product> getProducts() {
        return List.copyOf(products.values());
    }

    // Hämtar en produkt via dess ID
    public Optional<Product> getProductById(UUID id){
        return Optional.ofNullable(products.get(id));
    }

    // Uppdaterar pris på en produkt och sparar den i changedProducts
    public void updateProductPrice(UUID id, BigDecimal newPrice){
        if (!products.containsKey(id)) {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
        Product product = products.get(id);
        product.price(newPrice);
        changedProducts.add(product);
    }

    // Returnerar lista över produkter som ändrats
    public List<Product> getChangedProducts() {
        return List.copyOf(changedProducts);
    }

    // Returnerar alla produkter som är utgångna
    public List<Perishable> expiredProducts(){
        return products.values().stream()
                .filter(product -> product instanceof Perishable)
                .map(product -> (Perishable) product)
                .filter(Perishable::isExpired)
                .toList();
    }

    // Returnerar alla produkter som går att skicka
    public List<Shippable> shippableProducts(){
        return products.values().stream()
                .filter(product -> product instanceof Shippable)
                .map(product -> (Shippable) product)
                .toList();
    }

    // Tar bort en produkt
    public void remove(UUID id){
            products.remove(id);
    }

    // Grupperar produkter efter kategori
    public Map<Category, List<Product>> getProductsGroupedByCategories(){
        if (products.isEmpty()) return Collections.emptyMap();

        return products.values().stream()
                .collect(Collectors.groupingBy(Product::category));
    }
    public static Warehouse getInstance(){
        return getInstance("DefaultWarehouse");
    }

}
