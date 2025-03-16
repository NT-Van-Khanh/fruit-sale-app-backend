package com.example.fruit_selling.controller;

import com.example.fruit_selling.dto.ProductSimpleDTO;
import com.example.fruit_selling.projection.ProductProjection;
import com.example.fruit_selling.model.Product;
import com.example.fruit_selling.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductSimpleDTO>> getAllProduct() {
        return ResponseEntity.ok(productService.getProductsForHomepage());
    }


    @GetMapping("/top-5-product")
    public  ResponseEntity<List<ProductSimpleDTO>>  get5Product(){
        return ResponseEntity.ok(productService.getTop5Product());
    }
//
//    @GetMapping("/homepage")
//    public ResponseEntity<List<ProductProjection>> getProductForHomePage(){
//        return ResponseEntity.ok(productService.getProductsForHomepage());
//    }


    @GetMapping("/homepage")
    public ResponseEntity<List<ProductSimpleDTO>> getProductForHomePage() {
        return ResponseEntity.ok(productService.getProductsForHomepage());
    }

}
