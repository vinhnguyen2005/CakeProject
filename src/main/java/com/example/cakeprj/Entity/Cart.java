package com.example.cakeprj.Entity;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String cakeID;
    private String name;
    private String imageURL;

    @Column(nullable = true)
    private String size;
    private int quantity;
    private double price;
    private double unitprice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public Cart(String cakeID, String name, String imageURL, String size, int quantity, double price, Users user, double unitprice) {
        this.cakeID = cakeID;
        this.name = name;
        this.imageURL = imageURL;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
        this.unitprice = unitprice;
    }


    @Transient
    private String formattedPrice;

    public String getFormattedPrice() {
        return formattedPrice;
    }



    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public Cart() {
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCakeID() {
        return cakeID;
    }

    public void setCakeID(String cakeID) {
        this.cakeID = cakeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.price = this.unitprice * quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}