package me.kyeongho.orderservice.service;

import me.kyeongho.orderservice.dto.OrderDto;
import me.kyeongho.orderservice.entity.OrderEntity;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getAllOrdersByUserId(String userId);
}
