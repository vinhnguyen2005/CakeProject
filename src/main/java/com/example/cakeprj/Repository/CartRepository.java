package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.Cart;
import com.example.cakeprj.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    List<Cart> findByUser(Users user);
    Optional<Cart> findByNameAndSizeAndUser(String name, String size, Users user);
}
