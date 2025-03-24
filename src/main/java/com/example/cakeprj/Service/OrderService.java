package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.*;
import com.example.cakeprj.Repository.OrderDetailsRepository;
import com.example.cakeprj.Repository.OrderRepository;
import com.example.cakeprj.Repository.UserRepository;
import com.example.cakeprj.dto.request.CategoryIncomeDTO;
import com.example.cakeprj.dto.request.MonthlyOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public Long countOrderByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.countByStatus(orderStatus);
    }

    public Long countAllOrders(){
        return orderRepository.countOrdersWithoutAdmin();
    }

    public long getNewOrdersCount() {
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
        return orderRepository.countOrdersByDate(startOfDay, endOfDay);
    }


    public boolean isCakeInActiveOrders(String cakeId) {
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findByCakeId(cakeId);
        for (OrderDetails orderDetails : orderDetailsList) {

            if (!orderDetails.getOrder().getStatus().equals(OrderStatus.DA_HUY) &&
                    !orderDetails.getOrder().getStatus().equals(OrderStatus.DA_GIAO)) {
                return true;
            }
        }
        return false;
    }

    public List<CategoryIncomeDTO> getCategoryIncome() {
        return orderRepository.getIncomeByCategory();
    }

    public List<MonthlyOrderDTO> getOrderCountPerMonth() {
        List<Object[]> results = orderRepository.getOrderCountPerMonth();
        return results.stream()
                .map(row -> new MonthlyOrderDTO(
                        ((Number) row[0]).intValue(),
                        ((Number) row[1]).intValue(),
                        ((Number) row[2]).longValue()
                ))
                .collect(Collectors.toList());
    }

}
