package com.example.ProductCatalog.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal; // Using BigDecimal for currency for precision

// Marks this class as a JPA entity, mapped to a database table
@Entity
@Table(name = "products") // Specifies the table name in the database
public class Product {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
    private Long id; // Unique identifier for the product

    private String name; // Name of the product
    private BigDecimal price; // Price of the product, using BigDecimal for accuracy
    private String category; // Category of the product (e.g., "Electronics", "Books")

    // Default constructor (required by JPA)
    public Product() {
    }

    // Constructor with fields (useful for creating new product instances)
    public Product(String name, BigDecimal price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // --- Getters and Setters ---
    // These methods allow access and modification of the product's attributes

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Optional: Override toString() for better logging and debugging
    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", category='" + category + '\'' +
               '}';
    }
}

