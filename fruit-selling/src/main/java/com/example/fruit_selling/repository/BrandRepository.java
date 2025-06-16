package com.example.fruit_selling.repository;

import com.example.fruit_selling.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, String> {
    List<Brand> findByFlag(boolean flag);
}
