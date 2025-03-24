package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.Order;
import com.example.cakeprj.Entity.OrderDetails;
import com.example.cakeprj.Entity.OrderStatus;
import com.example.cakeprj.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.UUID;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, UUID> {
    List<OrderDetails> findByOrder(Order order);
    @Query("SELECT COUNT(od) > 0 FROM OrderDetails od WHERE od.cake.id = :cakeId AND od.order.status NOT IN (:deletableStatuses)")
    boolean existsInActiveOrders(@Param("cakeId") String cakeId, @Param("deletableStatuses") List<OrderStatus> deletableStatuses);

    @Query("SELECT od FROM OrderDetails od WHERE od.cake.id = :cakeid")
    List<OrderDetails> findByCakeId(@Param("cakeid") String cakeid);

    @Modifying
    @Query("UPDATE OrderDetails od SET od.cake = NULL WHERE od.cake.id = :cakeId")
    void setCakeToNull(@Param("cakeId") String cakeId);

}

