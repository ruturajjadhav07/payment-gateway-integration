package com.example.securepay.service.order;

import com.example.securepay.dtos.order.OrderRequest;
import com.example.securepay.dtos.order.OrderResponse;

import java.util.List;

public interface OrderService {
    // Create order
    OrderResponse createOrder (OrderRequest request);

    // Get order details of particular user
    List<OrderResponse> getOrderDetailsOfUser();
}
