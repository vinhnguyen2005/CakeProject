package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("SELECT c.name FROM Category c WHERE c.id = :id")
    String findNameById(String id);
}
