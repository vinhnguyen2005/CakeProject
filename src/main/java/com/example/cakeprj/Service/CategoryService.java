package com.example.cakeprj.Service;

import com.example.cakeprj.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String getCategoryName(String id) {
        return categoryRepository.findNameById(id);
    }
}
