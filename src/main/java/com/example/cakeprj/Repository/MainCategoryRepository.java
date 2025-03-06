package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategory, String> {
    Optional<MainCategory> findNameById(String id);
}