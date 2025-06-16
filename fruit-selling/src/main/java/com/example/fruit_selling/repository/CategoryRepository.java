package com.example.fruit_selling.repository;

import com.example.fruit_selling.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    List<Category> findByFlag(boolean b);
}
