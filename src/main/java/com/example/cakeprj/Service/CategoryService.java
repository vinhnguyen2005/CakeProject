package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.Category;
import com.example.cakeprj.Entity.MainCategory;
import com.example.cakeprj.Repository.CategoryRepository;
import com.example.cakeprj.Repository.MainCategoryRepository;
import com.example.cakeprj.dto.request.SubCategoryCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MainCategoryRepository mainCategoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, MainCategoryRepository mainCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.mainCategoryRepository = mainCategoryRepository;
    }

    public boolean addSubCategory(String mainCategoryID, String subCategoryID, String subCategoryName) {
        MainCategory mainCategory = mainCategoryRepository.findById(mainCategoryID)
                .orElseThrow(() -> new RuntimeException("Main Category not found"));

        if (categoryRepository.existsById(subCategoryID)) {
            return false;
        }

        Category subcategory = new Category();
        subcategory.setId(subCategoryID);
        subcategory.setName(subCategoryName);
        subcategory.setMainCategory(mainCategory);

        categoryRepository.save(subcategory);
        return true;
    }

    public boolean updateSubCategory(String categoryID, String categoryName, String mainCategoryID) {
        MainCategory mainCategory = mainCategoryRepository.findById(mainCategoryID)
                .orElseThrow(() -> new RuntimeException("Main Category not found"));

        Category subCategory = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new RuntimeException("SubCategory not found"));

        subCategory.setName(categoryName);
        subCategory.setMainCategory(mainCategory);

        categoryRepository.save(subCategory);
        return true;
    }

    public Category getSubCategoryByID(String subCategoryID) {
        return categoryRepository.findById(subCategoryID).orElseThrow(() -> new RuntimeException("SubCategory not found"));
    }



    public List<SubCategoryCreationRequest> getSubCategories(String mainCategoryID) {
        List<Category> subcategoriesList = categoryRepository.findCategoriesByMainCategoryId(mainCategoryID);

        return subcategoriesList.stream().map(category -> new SubCategoryCreationRequest(category.getId(),
                        category.getName(),
                        categoryRepository.countCakesInCategory(category.getId())))
                .collect(Collectors.toList());
    }

    public List<Category> getCategoriesByMainCategory(String mainCategoryID) {
        return categoryRepository.findCategoriesByMainCategoryId(mainCategoryID);
    }


    public String getCategoryName(String subCategoryId) {
        return categoryRepository.findNameById(subCategoryId);
    }

    public int getCakeCount(String subcategoryID) {
        return categoryRepository.countCakesInCategory(subcategoryID);
    }

    public void deleteSubcategory(String subCategoryID) {
        categoryRepository.deleteById(subCategoryID);
    }
}