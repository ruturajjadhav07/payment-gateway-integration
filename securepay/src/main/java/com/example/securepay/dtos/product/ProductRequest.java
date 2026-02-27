package com.example.securepay.dtos.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    // Product Request DTO

    @NotBlank(message = "Product name cannot be empty")
    @Size(min = 4, message = "Product should have minimum 4 characters")
    private String productName;

    @NotBlank(message = "Product description cannot be empty")
    @Size(min = 10, max = 30, message = "Product description should have minimum 10 characters and max 30 characters")
    private String productDescription;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "10.0", inclusive = true, message = "Price should be greater than or equal to 10")
    private BigDecimal productPrice;

    @Min(value = 1, message = "Product quantity must be at least 1")
    private int productQuantity;

    @NotBlank(message = "Product photo URL cannot be empty")
    @URL(message = "Invalid URL format")
    private String productImage;
}