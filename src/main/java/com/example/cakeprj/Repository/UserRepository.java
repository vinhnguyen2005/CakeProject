package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Users> findByResetToken(String resetToken);
    @Query("SELECT COUNT(u) FROM Users u JOIN u.roles r WHERE r.name = :roleName")
    Long countUsersByRole(@Param("roleName") String roleName);
    @Query("SELECT COUNT(u) FROM Users u WHERE NOT EXISTS " +
            "(SELECT 1 FROM u.roles r WHERE r.name = 'ROLE_ADMIN')")
    Long countUsersWithoutAdmin();

}
