package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.Order;
import com.example.cakeprj.Entity.OrderStatus;
import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.dto.request.CategoryIncomeDTO;
import com.example.cakeprj.dto.request.MonthlyOrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUser(Users user);

    List<Order> findByUserIdOrderByOrderDateDesc(UUID user_id);

    List<Order> findByStatus(OrderStatus orderStatus);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :orderStatus " +
            "AND NOT EXISTS (SELECT 1 FROM Users u JOIN u.roles r WHERE u.id = o.user.id AND r.name = 'ROLE_ADMIN')")
    Long countByStatus(@Param("orderStatus") OrderStatus orderStatus);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.orderDate >= :startOfDay AND o.orderDate < :endOfDay " +
            "AND NOT EXISTS (SELECT 1 FROM Users u JOIN u.roles r WHERE u.id = o.user.id AND r.name = 'ROLE_ADMIN')")
    Long countOrdersByDate(@Param("startOfDay") LocalDateTime startOfDay,
                           @Param("endOfDay") LocalDateTime endOfDay);
    @Query("SELECT COUNT(o) FROM Order o WHERE NOT EXISTS " +
            "(SELECT 1 FROM Users u JOIN u.roles r WHERE u.id = o.user.id AND r.name = 'ROLE_ADMIN')")
    Long countOrdersWithoutAdmin();

    @Query(value = "SELECT CAST(YEAR(o.order_date) AS SIGNED), " +
            "CAST(MONTH(o.order_date) AS SIGNED), " +
            "COUNT(o.id) " +
            "FROM orders o " +
            "WHERE NOT EXISTS ( " +
            "    SELECT 1 FROM users u " +
            "    JOIN user_roles ur ON u.id = ur.user_id " +
            "    JOIN roles r ON ur.role_id = r.id " +
            "    WHERE u.id = o.user_id AND r.name = 'ROLE_ADMIN' " +
            ") " +
            "GROUP BY CAST(YEAR(o.order_date) AS SIGNED), CAST(MONTH(o.order_date) AS SIGNED) " +
            "ORDER BY CAST(YEAR(o.order_date) AS SIGNED), CAST(MONTH(o.order_date) AS SIGNED)",
            nativeQuery = true)
    List<Object[]> getOrderCountPerMonth();


    @Query("SELECT new com.example.cakeprj.dto.request.CategoryIncomeDTO(cat.id, cat.name, CAST(SUM(od.price) AS double)) " +
            "FROM OrderDetails od " +
            "JOIN od.order o " +
            "JOIN o.user u " +
            "JOIN u.roles r " +
            "JOIN od.cake c " +
            "JOIN c.categories cat " +
            "WHERE o.status NOT IN (com.example.cakeprj.Entity.OrderStatus.CHO_XAC_NHAN, com.example.cakeprj.Entity.OrderStatus.DA_HUY) " +
            "AND r.name <> 'ROLE_ADMIN' " +
            "GROUP BY cat.id, cat.name")
    List<CategoryIncomeDTO> getIncomeByCategory();
}
