package com.example.cakeprj.Entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cakeproduct")
public class Cake {
    @Id
    private String id;

    private String name;
    private Double price;
    private String imageURL;
    private Boolean hasSize;

    @ManyToMany
    @JoinTable(
            name = "cake_categories",
            joinColumns = @JoinColumn(name = "cakeID"),
            inverseJoinColumns = @JoinColumn(name = "categoryID")
    )
    private Set<Category> categories;

    @Transient
    private String formattedPrice;

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Boolean getHasSize() {
        return hasSize;
    }

    public void setHasSize(Boolean hasSize) {
        this.hasSize = hasSize;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
