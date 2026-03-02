package com.example.securepay.service.order;

import com.example.securepay.dtos.order.OrderRequest;
import com.example.securepay.dtos.order.OrderResponse;

public interface OrderService {
    OrderResponse createOrder (OrderRequest request);
}
