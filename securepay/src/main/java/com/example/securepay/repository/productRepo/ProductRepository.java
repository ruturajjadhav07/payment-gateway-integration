package com.example.securepay.repository.productRepo;

import com.example.securepay.entities.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Exist by product name
    boolean existsByProductName(String productName);
}
