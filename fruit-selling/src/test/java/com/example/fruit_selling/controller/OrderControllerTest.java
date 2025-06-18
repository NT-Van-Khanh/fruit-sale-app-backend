package com.example.fruit_selling.controller;

import com.example.fruit_selling.dto.CustomerDTO;
import com.example.fruit_selling.dto.OrderDTO;
import com.example.fruit_selling.dto.OrderItemDTO;
import com.example.fruit_selling.dto.OrderResponseDTO;
import com.example.fruit_selling.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser
    void testCreateOrder() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setLastName("Nguyễn Văn");
        customer.setFirstName(" A");
        customer.setEmail("a@example.com");
        customer.setPhone("0123456789");
        customer.setAddress("123 Trần Phú, Hà Nội");
        OrderItemDTO item = new OrderItemDTO();
        item.setProductId("SP001");
        item.setQuantity(2);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomer(customer);
        orderDTO.setTotalCost(150000L);
        orderDTO.setAddress("123 Trần Phú, Hà Nội");
        orderDTO.setPayStatus("PAID");
        orderDTO.setShipStatus("SHIPPING");
        orderDTO.setNote("Giao giờ hành chính");
        orderDTO.setItems(List.of(item));
        OrderResponseDTO mockResponse = new OrderResponseDTO();
        mockResponse.setId("ORD001");
        mockResponse.setTotalCost(150000L);
        mockResponse.setLastUpdate(LocalDateTime.now());
        Mockito.when(orderService.addOrder(Mockito.any(OrderDTO.class),Mockito.eq("022233")))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/api/orders/add")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("otp", "022233")
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value("ORD001"))
                .andExpect(jsonPath("$.data.totalCost").value(150000L));
    }
}
