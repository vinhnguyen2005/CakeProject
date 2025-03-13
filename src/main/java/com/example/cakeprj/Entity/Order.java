package com.example.cakeprj.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @UuidGenerator
    private UUID id;

    private LocalDateTime orderDate;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String fullName;
    private String phone;
    private String address;
    @Column(columnDefinition = "TEXT")
    private String note;
    private String city;
    @Transient
    private String formattedPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetails> orderDetails;


    public Order() {
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.CHO_XAC_NHAN;
    }

    // Getters và Setters

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public Set<OrderDetails> getOrderDetails() { return orderDetails; }
    public void setOrderDetails(Set<OrderDetails> orderDetails) { this.orderDetails = orderDetails; }
}
