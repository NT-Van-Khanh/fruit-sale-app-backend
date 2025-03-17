package com.example.fruit_selling.service;

import com.example.fruit_selling.dto.ProductDTO;
import com.example.fruit_selling.dto.ProductSimpleDTO;
import com.example.fruit_selling.projection.ProductProjection;
import com.example.fruit_selling.model.Product;
import com.example.fruit_selling.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private static final String FIREBASE_STORAGE_URL = "https://firebasestorage.googleapis.com/v0/b/%s/o/%s.png?alt=media";
    private static final String FIREBASE_BUCKET = "retailstorage-5432c.appspot.com"; // 🔹 Thay bằng Firebase Bucket của bạn

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại!"));
        return new ProductDTO(product, String.format(FIREBASE_STORAGE_URL, FIREBASE_BUCKET, id));
    }

    public ProductSimpleDTO getProductSimpleById(String id){
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) return null;
        return new ProductSimpleDTO(product.getId(),
                                    product.getName(),
                                    product.getPrice(),
                                    product.getQuantity(),
                                    product.getUnit().getName(),
                                    String.format(FIREBASE_STORAGE_URL, FIREBASE_BUCKET, id));
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public List<ProductSimpleDTO> getTop5Product() {
        List<Product> products = productRepository.findTop5ByOrderByIdDesc(); // 🔥 Lấy 8 sản phẩm mới nhất

        return products.stream().map(product ->
                new ProductSimpleDTO(
                        product.getId(),  // Chuyển ID sang String
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getUnit().getName(),
                        String.format(FIREBASE_STORAGE_URL, FIREBASE_BUCKET, product.getId()) // Chuyển đổi URL ảnh
                )
        ).collect(Collectors.toList());
    }
//    public List<ProductProjection> getProductsForHomepage(){
//        return productRepository.findAllProjectedBy();
//    }
    public List<ProductSimpleDTO> getProductsForHomepage() {
        List<Product> products = productRepository.findAll(); // 🔥 Lấy 8 sản phẩm mới nhất
        return products.stream().map(product ->
                new ProductSimpleDTO(
                        product.getId().toString(),  // Chuyển ID sang String
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getUnit().getName(),
                        String.format(FIREBASE_STORAGE_URL, FIREBASE_BUCKET, product.getId()) // Chuyển đổi URL ảnh
                )
        ).collect(Collectors.toList());
    }

    public Integer getProductQuantityById(String id){
        return productRepository.getProductQuantityById(id);
    }
}
