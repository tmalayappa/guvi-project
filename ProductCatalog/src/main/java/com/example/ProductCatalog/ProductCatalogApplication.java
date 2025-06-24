package com.example.ProductCatalog;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ProductCatalog.Entity.Product;
import com.example.ProductCatalog.Service.ProductService;

@SpringBootApplication // Marks this as a Spring Boot application
public class ProductCatalogApplication {

    public static void main(String[] args) {
        // Starts the Spring Boot application
        SpringApplication.run(ProductCatalogApplication.class, args);
    }
        @Bean
    public CommandLineRunner demoData(ProductService productService) {
        return args -> {
            System.out.println("Inserting sample product data...");

            // initial products
            productService.saveProduct(new Product("Laptop Pro", new BigDecimal("1200.00"), "Electronics"));
            productService.saveProduct(new Product("Mechanical Keyboard", new BigDecimal("95.50"), "Electronics"));
            productService.saveProduct(new Product("Learning Spring Boot", new BigDecimal("45.99"), "Books"));
            productService.saveProduct(new Product("Ergonomic Mouse", new BigDecimal("30.25"), "Electronics"));
            productService.saveProduct(new Product("Coffee Mug", new BigDecimal("12.00"), "Home Goods"));
            productService.saveProduct(new Product("IPhone 16 Pro", new BigDecimal("4800.00"), "Electronics"));
            productService.saveProduct(new Product("Nothing Phone 3a", new BigDecimal("1700.00"), "Electronics"));
            productService.saveProduct(new Product("Harry Potter and the Cursed Child", new BigDecimal("45.00"), "Books"));
            productService.saveProduct(new Product("Harry Potter and the Deathly Hallows", new BigDecimal("45.00"), "Books"));
            productService.saveProduct(new Product("Harry Potter and the Half-Blood Prince", new BigDecimal("45.00"), "Books"));
            productService.saveProduct(new Product("Harry Potter and the Order of the Phoenix", new BigDecimal("45.00"), "Books"));
            productService.saveProduct(new Product("Harry Potter and the Goblet of Fire", new BigDecimal("45.00"), "Books"));
            productService.saveProduct(new Product("Harry Potter and the Prisoner of Azkaban", new BigDecimal("45.00"), "Books"));
            productService.saveProduct(new Product("Harry Potter and the Chamber of Secrets", new BigDecimal("45.00"), "Books"));
            productService.saveProduct(new Product("Harry Potter and the Sorcerer's Stone", new BigDecimal("45.00"), "Books"));
            productService.saveProduct(new Product("Marvel Mug", new BigDecimal("12.00"), "Home Goods"));

            System.out.println("Sample product data inserted successfully.");
        };
    }

}
