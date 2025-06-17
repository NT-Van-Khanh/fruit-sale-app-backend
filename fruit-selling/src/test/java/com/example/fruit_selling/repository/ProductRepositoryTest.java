package com.example.fruit_selling.repository;

import com.example.fruit_selling.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // nếu muốn test với DB thật
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Category category;
    private Brand brand;
    private Unit unit;
    private Employee employee;

    @BeforeEach
    void setUp() {
        category = entityManager.persist(new Category("C01", "Trái cây"));
        brand = entityManager.persist(new Brand("B01", "Đà Lạt"));
        unit = entityManager.persist(new Unit("U01", "1kg"));
        employee = entityManager.persist(Employee.builder()
                .id("E01")
                .lastName("Nguyễn")
                .firstName("Văn A")
                .phone("0987654321")
                .email("vana@example.com")
                .birthday(LocalDate.of(1995, 1, 1))  // ngày sinh không được null
                .flag(false)                          // flag cũng bắt buộc
                .gender(true)                       // giới tính nếu không null
                .password("123456")                  // mật khẩu
                .build());
    }

    @Test
    void testSearchProducts() {
        Product product = Product.builder()
                .id("SP0001")
                .name("Dâu Tây Đà Lạt")
                .detail("Ngọt, tươi")
                .quantity(20)
                .price(50000L)
                .category(category)
                .brand(brand)
                .unit(unit)
                .employee(employee)
                .flag(true)
                .build();
        entityManager.persist(product);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> result = productRepository.searchProducts("dâu", category.getId(), brand.getId(), null, null, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("SP0001", result.getContent().get(0).getId());
    }

    @Test
    void testFindTop5ByOrderByIdDesc() {
        for (int i = 1; i <= 6; i++) {
            Product p = Product.builder()
                    .id("SP0000" + i)
                    .name("Product " + i)
                    .detail("Test")
                    .quantity(10)
                    .price(1000L)
                    .category(category)
                    .brand(brand)
                    .unit(unit)
                    .employee(employee)
                    .flag(true)
                    .build();
            entityManager.persist(p);
        }

        List<Product> top5 = productRepository.findTop5ByOrderByIdDesc();
        assertEquals(5, top5.size());
        assertEquals("SP00006", top5.get(0).getId());
    }

    @Test
    void testGetProductQuantityById() {
        Product product = Product.builder()
                .id("SP0010")
                .name("Xoài")
                .detail("Chín cây")
                .quantity(50)
                .price(30000L)
                .category(category)
                .brand(brand)
                .unit(unit)
                .employee(employee)
                .flag(true)
                .build();
        entityManager.persist(product);

        Integer qty = productRepository.getProductQuantityById("SP0010");
        assertEquals(50, qty);
    }
}
