package com.example.securepay.dtos.order;

import com.example.securepay.entities.order.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponse {

    private Long orderId;
    private Long userId;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private List<OrderItemResponse> items;
}
