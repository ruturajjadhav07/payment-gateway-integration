package com.example.securepay.service.product;

import com.example.securepay.dtos.product.ProductRequest;
import com.example.securepay.dtos.product.ProductResponse;

import java.util.List;

public interface ProductService {
    // Create product
    ProductResponse createProduct(ProductRequest request);

    // Get all products
    List<ProductResponse> getAllProducts();


}
