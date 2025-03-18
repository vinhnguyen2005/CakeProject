package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.Order;
import com.example.cakeprj.Entity.OrderStatus;
import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.dto.request.CategoryIncomeDTO;
import com.example.cakeprj.dto.request.MonthlyOrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUser(Users user);
    List<Order> findByUserIdOrderByOrderDateDesc(UUID user_id);
    List<Order> findByStatus(OrderStatus orderStatus);
    Long countByStatus(OrderStatus orderStatus);
    @Query("SELECT COUNT(o) FROM Order o WHERE o.orderDate >= :startOfDay AND o.orderDate < :endOfDay")
    public Long countOrdersByDate(LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query(value = "SELECT CAST(YEAR(o.order_date) AS SIGNED), " +
            "CAST(MONTH(o.order_date) AS SIGNED), " +
            "COUNT(o.id) " +
            "FROM orders o " +
            "GROUP BY CAST(YEAR(o.order_date) AS SIGNED), CAST(MONTH(o.order_date) AS SIGNED) " +
            "ORDER BY CAST(YEAR(o.order_date) AS SIGNED), CAST(MONTH(o.order_date) AS SIGNED)",
            nativeQuery = true)
    List<Object[]> getOrderCountPerMonth();



    @Query("SELECT new com.example.cakeprj.dto.request.CategoryIncomeDTO(cat.id, cat.name, CAST(SUM(od.quantity * od.price) AS double)) " +
            "FROM OrderDetails od " +
            "JOIN od.cake c " +
            "JOIN c.categories cat " +
            "GROUP BY cat.id, cat.name")
    List<CategoryIncomeDTO> getIncomeByCategory();



}
