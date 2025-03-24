package com.example.cakeprj.Entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "main_category_id", nullable = false)
    private MainCategory mainCategory;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<Cake> cakes;

    public Category() {}

    public Category(String name, MainCategory mainCategory) {
        this.name = name;
        this.mainCategory = mainCategory;
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

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    public Set<Cake> getCakes() {
        return cakes;
    }

    public void setCakes(Set<Cake> cakes) {
        this.cakes = cakes;
    }
}
