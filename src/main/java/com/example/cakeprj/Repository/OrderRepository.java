package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.Order;
import com.example.cakeprj.Entity.OrderDetails;
import com.example.cakeprj.Entity.OrderStatus;
import com.example.cakeprj.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUser(Users user);
    List<Order> findByUserIdOrderByOrderDateDesc(UUID user_id);
    List<Order> findByStatus(OrderStatus orderStatus);
}
