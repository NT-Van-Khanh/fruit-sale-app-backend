package com.example.fruit_selling.controller;

import com.example.fruit_selling.config.SecurityConfig;
import com.example.fruit_selling.dto.ProductDTO;
import com.example.fruit_selling.dto.ProductSimpleDTO;
import com.example.fruit_selling.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductSimpleDTO product;

    @BeforeEach
    void setUp() {
        product = ProductSimpleDTO.builder()
                .id("SP0001")
                .name("Dâu Tây")
                .price(50000L)
                .quantity(10)
                .build();
    }



    @Test
    @WithMockUser
    void testGetProductById() throws Exception {
        ProductDTO mockProduct = new ProductDTO();
        mockProduct.setId("SP001");
        mockProduct.setName("Dưa Hấu");

        Mockito.when(productService.getProductById("SP001")).thenReturn(mockProduct);

        mockMvc.perform(get("/api/products/SP001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("SP001"))
                .andExpect(jsonPath("$.data.name").value("Dưa Hấu"));
    }

    @Test
    @WithMockUser
    void testGetTop5Product() throws Exception {
        List<ProductSimpleDTO> mockList = Arrays.asList(
                ProductSimpleDTO.builder()
                        .id("SP001")
                        .name("Táo")
                        .price(12000L)
                        .build(), ProductSimpleDTO.builder()
                        .id("SP002")
                        .name("Xoài")
                        .price(12000L)
                        .build()
        );

        Mockito.when(productService.getTop5Product()).thenReturn(mockList);

        mockMvc.perform(get("/api/products/top-5-product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value("SP001"))
                .andExpect(jsonPath("$.data[1].name").value("Xoài"));
    }

    @Test
    @WithMockUser
    void testCheckStock() throws Exception {
        Mockito.when(productService.getProductQuantityById("SP001")).thenReturn(50);

        mockMvc.perform(get("/api/products/check-stock/SP001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(50));
    }

}