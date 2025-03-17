package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.*;
import com.example.cakeprj.Repository.OrderDetailsRepository;
import com.example.cakeprj.Repository.OrderRepository;
import com.example.cakeprj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrders(UUID userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUserIdOrderByOrderDateDesc(user.getId());
    }

    public List<OrderDetails> getOrderDetails(UUID orderId) {
        Order foundOrder = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return orderDetailsRepository.findByOrder(foundOrder);
    }

    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findByStatus(orderStatus);
    }

    public List<OrderStatus> getAllStatuses() {
        return Arrays.asList(OrderStatus.values());
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

}
