package com.example.fruit_selling.repository;

import com.example.fruit_selling.projection.ProductProjection;
import com.example.fruit_selling.model.Product;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findAllProjectedBy();
    @Query("SELECT p.id AS id, p.name AS name, p.price AS price FROM Product p")
    List<Product> findSimpleProducts();

    List<Product> findTop5ByOrderByIdDesc();
}

