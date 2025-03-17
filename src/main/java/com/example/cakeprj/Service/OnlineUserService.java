package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.OnlineUsers;
import com.example.cakeprj.Repository.OnlineUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlineUserService {

    private final OnlineUserRepository onlineUserRepository;

    public OnlineUserService(OnlineUserRepository onlineUserRepository) {
        this.onlineUserRepository = onlineUserRepository;
    }

    public void saveOnlineUser(OnlineUsers onlineUser) {
        onlineUserRepository.save(onlineUser);
    }

    public List<OnlineUsers> getAllOnlineUsers() {
        return onlineUserRepository.findAll();
    }
}
