package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.OnlineUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OnlineUserRepository extends JpaRepository<OnlineUsers, UUID> {
}
