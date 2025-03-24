package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("SELECT c.name FROM Category c WHERE c.id = :id")
    String findNameById(String id);

    List<Category> findCategoriesByMainCategoryId(String mainCategoryId);

    @Query("SELECT COUNT(c) FROM Category cat JOIN cat.cakes c WHERE cat.id = :categoryId")
    int countCakesInCategory(@Param("categoryId") String categoryId);

    @Modifying
    @Query(value = "DELETE FROM cake_categories WHERE categoryID = :categoryId", nativeQuery = true)
    void removeCategoryFromCakes(@Param("categoryId") String categoryId);

    Optional<Category> findById(String id);
}
