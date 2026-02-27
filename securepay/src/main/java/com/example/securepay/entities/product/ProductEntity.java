package com.example.securepay.entities.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name="product_name", nullable = false)
    private String productName;

    @Column(name="product_description", nullable = false)
    private String productDescription;

    @Column(name="product_price", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_quantity", nullable = false)
    private int productQuantity;

    @Lob
    @Column(name="product_image", nullable = false, columnDefinition = "LONGTEXT")
    private String productImage;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
