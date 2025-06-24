package com.example.ProductCatalog.Repository;

import com.example.ProductCatalog.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Marks this interface as a Spring Data JPA repository
@Repository
// Extends JpaRepository to inherit standard CRUD operations (Create, Read, Update, Delete)
// <Product, Long> specifies the entity type and the type of its primary key
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Custom repository method to find all products belonging to a specific category.
     * Spring Data JPA automatically generates the query for this method based on its name.
     *
     * @param category The category name to search for.
     * @return A list of products that belong to the specified category.
     */
    List<Product> findByCategory(String category);
}
