package com.example.fruit_selling.controller;

import com.example.fruit_selling.dto.ProductDTO;
import com.example.fruit_selling.dto.ProductSimpleDTO;
import com.example.fruit_selling.projection.ProductProjection;
import com.example.fruit_selling.model.Product;
import com.example.fruit_selling.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductSimpleDTO>> getAllProduct() {
        return ResponseEntity.ok(productService.getProductsForHomepage());
    }


    @GetMapping("/products/top-5-product")
    public  ResponseEntity<List<ProductSimpleDTO>>  get5Product(){
        return ResponseEntity.ok(productService.getTop5Product());
    }
//
//    @GetMapping("/homepage")
//    public ResponseEntity<List<ProductProjection>> getProductForHomePage(){
//        return ResponseEntity.ok(productService.getProductsForHomepage());
//    }


    @GetMapping("/products/homepage")
    public ResponseEntity<List<ProductSimpleDTO>> getProductForHomePage() {
        return ResponseEntity.ok(productService.getProductsForHomepage());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/product/simple/{id}")
    public ResponseEntity<ProductSimpleDTO> getProductSimpleById(@PathVariable String id){
        return ResponseEntity.ok(productService.getProductSimpleById(id));
    }

    @GetMapping("/product/check-stock/{id}")
    public ResponseEntity<Integer> getProductQuantityById(@PathVariable String id) {
        Integer quantity = productService.getProductQuantityById(id);

        if (quantity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0); // Trả về 0 và mã 404 nếu sản phẩm không tồn tại

        return ResponseEntity.ok(quantity); // Trả về số lượng tồn kho với mã 200 OK
    }
}
