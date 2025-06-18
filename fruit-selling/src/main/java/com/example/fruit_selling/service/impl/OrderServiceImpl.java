package com.example.fruit_selling.service.impl;

import com.example.fruit_selling.dto.OrderDTO;
import com.example.fruit_selling.dto.OrderItemDTO;
import com.example.fruit_selling.dto.OrderResponseDTO;
import com.example.fruit_selling.dto.PageResponse;
import com.example.fruit_selling.exception.ResourceNotFoundException;
import com.example.fruit_selling.mapper.OrderItemMapper;
import com.example.fruit_selling.mapper.OrderMapper;
import com.example.fruit_selling.model.Customer;
import com.example.fruit_selling.model.OrderItem;
import com.example.fruit_selling.model.OrderProduct;
import com.example.fruit_selling.model.Product;
import com.example.fruit_selling.repository.OrderRepository;
import com.example.fruit_selling.repository.ProductRepository;
import com.example.fruit_selling.service.*;
import jakarta.transaction.Transactional;
import org.hibernate.query.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {


    public static final String VERIFY = "VERIFY";
    public static final String GET_ORDER = "GET_ORDER";
    private final EmailService emailService;
    private final RedisService redisService;

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    public OrderServiceImpl(EmailService emailService,
                            RedisService redisService,
                            OrderRepository orderRepository, ProductRepository productRepository,
                            OrderItemService orderItemService,
                            CustomerService customerService) {
        this.emailService = emailService;
        this.redisService = redisService;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
    }
    @Override
    public void sendOtp(String email,  String purpose, String subject) {
        String otp = emailService.generateOtp();
        String content = "Xin chào,\n\n"
                + "Mã OTP của bạn là: " + otp + "\n"
                + "Hiệu lực trong 5 phút.\n\n"
                + "Trân trọng.";
        emailService.sendMail(email, subject, content);
        redisService.saveOtp(email, purpose, otp);
    }

    @Transactional
    @Override
    public OrderResponseDTO addOrder(OrderDTO orderDTO, String otp) {
        String savedOtp = redisService.getOtp(orderDTO.getCustomer().getEmail(), VERIFY);

        if (savedOtp == null || !savedOtp.equals(otp)) {
            throw new IllegalArgumentException("OTP không hợp lệ hoặc đã hết hạn.");
        }
        if (orderDTO.getItems() == null || orderDTO.getItems().isEmpty()) {
            throw new IllegalArgumentException("Đơn hàng phải có ít nhất một sản phẩm.");
        }
        Customer customer = customerService.addOrGetExisting(orderDTO.getCustomer());
        OrderProduct order = OrderMapper.toOrder(orderDTO);
        order.setId(generateOrderId());
        order.setCustomer(customer);
        List<OrderItem> orderItem = new ArrayList<>();
        for(OrderItemDTO item : orderDTO.getItems()){
            Product p = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Sản phẩm %s không tồn tại!",item.getProductId())));
            OrderItem orderItemEntity = OrderItemMapper.toEntity(item, p.getPrice());
            orderItemEntity.setOrder(order);
            orderItem.add(orderItemEntity);
        }
        order.setItems(orderItem);
        order = orderRepository.save(order);
        redisService.deleteOtp(orderDTO.getCustomer().getEmail(), "VERIFY");
        return getOrderById(order.getId());
    }

    private String generateOrderId() {
        long count = orderRepository.count() + 1;
        return String.format("HD%06d", count); // VD: HD00001
    }

    @Override
    public PageResponse<OrderResponseDTO> getOrdersByEmail(String email, String otp, com.example.fruit_selling.dto.Page pageRequest) {
        String savedOtp = redisService.getOtp(email, GET_ORDER);
        Pageable pageable = pageRequest.toPageable();
        if (savedOtp == null || !savedOtp.equals(otp)) {
            throw new IllegalArgumentException("OTP không hợp lệ hoặc đã hết hạn.");
        }
        redisService.deleteOtp(email, "VERIFY");
        Page<OrderProduct> page = orderRepository.findByCustomerEmail(email, pageable);
        List<OrderResponseDTO> orders =  page.getContent().stream()
                .map(OrderMapper::toResponseDTO)
                .toList();
        return new PageResponse<>(orders, page);
    }

    @Override
    public OrderResponseDTO getOrderById(String id) {
        OrderProduct order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy đơn hàng với ID: " + id));
        return OrderMapper.toResponseDTO(order);
    }
}
