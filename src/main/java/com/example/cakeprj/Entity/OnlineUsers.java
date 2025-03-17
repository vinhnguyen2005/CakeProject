package com.example.cakeprj.Entity;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "online_users")
public class OnlineUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String email;
    private String phone;
    private String orderContent;

    public OnlineUsers() {}

    public OnlineUsers(String name, String email, String phone, String orderContent) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.orderContent = orderContent;
    }

    // Getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }
}

