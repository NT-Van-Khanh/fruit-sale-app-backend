package com.example.fruit_selling.service.impl;

import com.example.fruit_selling.dto.OrderDTO;
import com.example.fruit_selling.dto.OrderItemDTO;
import com.example.fruit_selling.dto.OrderResponseDTO;
import com.example.fruit_selling.dto.PageResponse;
import com.example.fruit_selling.mapper.OrderMapper;
import com.example.fruit_selling.model.Customer;
import com.example.fruit_selling.model.OrderItem;
import com.example.fruit_selling.model.OrderProduct;
import com.example.fruit_selling.repository.OrderRepository;
import com.example.fruit_selling.service.CustomerService;
import com.example.fruit_selling.service.OrderItemService;
import com.example.fruit_selling.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
    }

    @Transactional
    @Override
    public OrderResponseDTO addOrder(OrderDTO orderDTO) {
        if (orderDTO.getItems() == null || orderDTO.getItems().isEmpty()) {
            throw new IllegalArgumentException("Đơn hàng phải có ít nhất một sản phẩm.");
        }
        Customer customer = customerService.add(orderDTO.getCustomer());

        OrderProduct order = OrderMapper.toOrder(orderDTO);
        order.setCustomer(customer);
        order = orderRepository.save(order);

        for(OrderItemDTO item : orderDTO.getItems()){
           orderItemService.add(item, order.getId());
        }

        return getOrderById(order.getId());
    }

    @Override
    public PageResponse<OrderResponseDTO> getOrdersByEmail(String email, String otp) {
        return null;
    }

    @Override
    public OrderResponseDTO getOrderById(String id) {
        OrderProduct order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy đơn hàng với ID: " + id));
        return OrderMapper.toResponseDTO(order);
    }
}
