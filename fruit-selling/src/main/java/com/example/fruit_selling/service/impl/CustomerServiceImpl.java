package com.example.fruit_selling.service.impl;

import com.example.fruit_selling.dto.CustomerDTO;
import com.example.fruit_selling.mapper.CustomerMapper;
import com.example.fruit_selling.model.Customer;
import com.example.fruit_selling.repository.CustomerRepository;
import com.example.fruit_selling.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Override
    public Customer add(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toEntity(customerDTO);
        customer.setPassword("ahdh2sjhdu3w23heje3gffrere21gg32g");
        return customerRepository.save(customer);
    }

    @Override
    public Customer addOrGetExisting(CustomerDTO dto) {
        return customerRepository.findByPhone(dto.getPhone())
                .orElseGet(() -> {
                    Customer customer = CustomerMapper.toEntity(dto);
                    customer.setPassword("ahdh2sjhdu3w23heje3gffrere21gg32g");
                    return customerRepository.save(customer);
                });
    }
}
