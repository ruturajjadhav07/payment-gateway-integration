package com.example.securepay.service.order;

import com.example.securepay.dtos.order.OrderItemRequest;
import com.example.securepay.dtos.order.OrderItemResponse;
import com.example.securepay.dtos.order.OrderRequest;
import com.example.securepay.dtos.order.OrderResponse;
import com.example.securepay.dtos.product.ProductResponse;
import com.example.securepay.entities.order.OrderEntity;
import com.example.securepay.entities.order.OrderItemEntity;
import com.example.securepay.entities.product.ProductEntity;
import com.example.securepay.entities.user.UserEntity;
import com.example.securepay.exception.InsufficientStockException;
import com.example.securepay.exception.ResourceNotFoundException;
import com.example.securepay.repository.order.OrderRepository;
import com.example.securepay.repository.productRepo.ProductRepository;
import com.example.securepay.repository.userRepo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        OrderEntity order = prepareOrderWithItems(request);

        OrderEntity savedOrder = orderRepository.save(order);

        return convertToOrderResponse(savedOrder);
    }

    private OrderEntity prepareOrderWithItems(OrderRequest request) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        OrderEntity order = OrderEntity.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        List<OrderItemEntity> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {

            ProductEntity product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Product not found with id: " + itemRequest.getProductId()
                            )
                    );

            if (product.getProductQuantity() < itemRequest.getQuantity()) {
                throw new InsufficientStockException(
                        "Not enough stock for product: " + product.getProductName()
                );
            }

            product.setProductQuantity(
                    product.getProductQuantity() - itemRequest.getQuantity()
            );

            BigDecimal itemTotal = product.getProductPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            totalAmount = totalAmount.add(itemTotal);

            OrderItemEntity orderItem = OrderItemEntity.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .price(product.getProductPrice())
                    .build();

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        return order;
    }

    private OrderResponse convertToOrderResponse(OrderEntity order) {

        List<OrderItemResponse> itemResponses = order.getOrderItems()
                .stream()
                .map(item -> OrderItemResponse.builder()
                        .productId(item.getProduct().getProductId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build())
                .toList();

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .userId(order.getUser().getId())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .items(itemResponses)
                .build();
    }
}