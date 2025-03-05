package com.example.cakeprj.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "main_category")
public class MainCategory {

    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "mainCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories;

    public MainCategory() {}

    public MainCategory(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { // Updated parameter type
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
