package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.Order;
import com.example.cakeprj.Entity.OrderDetails;
import com.example.cakeprj.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, UUID> {
    List<OrderDetails> findByOrder(Order order);
}

