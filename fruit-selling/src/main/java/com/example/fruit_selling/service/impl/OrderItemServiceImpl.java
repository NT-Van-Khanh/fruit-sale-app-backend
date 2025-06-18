package com.example.fruit_selling.service.impl;

import com.example.fruit_selling.dto.OrderItemDTO;
import com.example.fruit_selling.mapper.OrderItemMapper;
import com.example.fruit_selling.model.OrderItem;
import com.example.fruit_selling.model.OrderProduct;
import com.example.fruit_selling.model.Product;
import com.example.fruit_selling.repository.OrderItemRepository;
import com.example.fruit_selling.repository.ProductRepository;
import com.example.fruit_selling.service.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository,
                                ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public void add(OrderItemDTO orderItemDTO, String orderId) {
//        OrderItem orderItem = OrderItemMapper.toEntity(orderItemDTO);
//        Product product = productRepository.findById(orderItemDTO.getProductId())
//                                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm với ID: " + orderItemDTO.getProductId()));
//        orderItem.setOrder(OrderProduct.builder().id(orderId).build());
//        orderItem.setProduct(product);
//        System.err.println(product.getPrice()*orderItemDTO.getQuantity());
//        orderItem.setPrice(product.getPrice()*orderItemDTO.getQuantity());
//        orderItemRepository.save(orderItem);
    }
}
