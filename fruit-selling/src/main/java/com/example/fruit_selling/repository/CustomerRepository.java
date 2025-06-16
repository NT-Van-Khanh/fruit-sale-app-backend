package com.example.fruit_selling.repository;

import com.example.fruit_selling.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
