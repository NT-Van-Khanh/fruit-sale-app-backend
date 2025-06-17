package com.example.fruit_selling.controller;

import com.example.fruit_selling.dto.*;
import com.example.fruit_selling.service.ProductService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ProductSimpleDTO>>> getAllProduct() {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,productService.getProductsForHomepage()));
    }


    @GetMapping("/top-5-product")
    public  ResponseEntity<ApiResponse<List<ProductSimpleDTO>>>  get5Product(){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,productService.getTop5Product()));
    }


    @GetMapping("/homepage")
    public ResponseEntity<ApiResponse<List<ProductSimpleDTO>>> getProductForHomePage() {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,productService.getProductsForHomepage()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable String id){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,productService.getProductById(id)));
    }

    @GetMapping("/simple/{id}")
    public ResponseEntity<ApiResponse<ProductSimpleDTO>> getProductSimpleById(@PathVariable String id){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,productService.getProductSimpleById(id)));
    }

    @GetMapping("/check-stock/{id}")
    public ResponseEntity<ApiResponse<Integer>> getProductQuantityById(@PathVariable String id) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, productService.getProductQuantityById(id)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<ProductSimpleDTO>>> searchProducts (
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String brandId,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @ModelAttribute Page pageRequest){
        PageResponse<ProductSimpleDTO> page = productService.searchProducts(keyword,
                categoryId,
                brandId,
                minPrice,
                maxPrice,
                pageRequest);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, page));
    }
}
