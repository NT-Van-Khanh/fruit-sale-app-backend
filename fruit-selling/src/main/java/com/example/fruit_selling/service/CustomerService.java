package com.example.fruit_selling.service;

import com.example.fruit_selling.dto.CustomerDTO;
import com.example.fruit_selling.model.Customer;

public interface CustomerService {
    Customer add(CustomerDTO customerDTO);
}
