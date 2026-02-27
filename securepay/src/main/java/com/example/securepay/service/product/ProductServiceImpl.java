package com.example.securepay.service.product;

import com.example.securepay.dtos.product.ProductRequest;
import com.example.securepay.dtos.product.ProductResponse;
import com.example.securepay.entities.product.ProductEntity;
import com.example.securepay.exception.DuplicateResourceFound;
import com.example.securepay.repository.productRepo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    // Create product
    @Override
    public ProductResponse createProduct(ProductRequest request) {

        if(productRepository.existsByProductName(request.getProductName())){
            throw new DuplicateResourceFound("Product Name already Registered");
        }

        ProductEntity product = convertToProductEntity(request);
        product = productRepository.save(product);
        return convertToProductResponse(product);
    }



    // create ProductEntity to response
    private ProductEntity convertToProductEntity(ProductRequest request){
        return ProductEntity.builder()
                .productName(request.getProductName())
                .productDescription(request.getProductDescription())
                .productPrice(request.getProductPrice())
                .productQuantity(request.getProductQuantity())
                .productImage(request.getProductImage())
                .createdAt(LocalDateTime.now())
                .build();

    }

    // create productResponse
    private ProductResponse convertToProductResponse(ProductEntity product){
        return ProductResponse.builder()
                .id(product.getProductId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .productQuantity(product.getProductQuantity())
                .productImage(product.getProductImage())
                .createdAt(product.getCreatedAt())
                .build();
    }
}
