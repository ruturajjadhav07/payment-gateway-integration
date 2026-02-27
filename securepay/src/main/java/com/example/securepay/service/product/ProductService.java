package com.example.securepay.service.product;

import com.example.securepay.dtos.product.ProductRequest;
import com.example.securepay.dtos.product.ProductResponse;

public interface ProductService {
    // Create product
    ProductResponse createProduct(ProductRequest request);
}
