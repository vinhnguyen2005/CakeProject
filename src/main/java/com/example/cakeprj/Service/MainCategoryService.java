package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.MainCategory;
import com.example.cakeprj.Repository.MainCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainCategoryService {
    private final MainCategoryRepository mainCategoryRepository;

    public MainCategoryService(MainCategoryRepository mainCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
    }

    public Optional<MainCategory> getMainCategoryName(String id) {
        return mainCategoryRepository.findNameById(id);
    }

    public List<MainCategory> getAllMainCategory() {
        return mainCategoryRepository.findAll();
    }
}
