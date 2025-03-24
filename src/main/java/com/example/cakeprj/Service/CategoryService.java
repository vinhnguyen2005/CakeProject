package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.Cake;
import com.example.cakeprj.Entity.Category;
import com.example.cakeprj.Entity.MainCategory;
import com.example.cakeprj.Repository.CakeProductRepository;
import com.example.cakeprj.Repository.CategoryRepository;
import com.example.cakeprj.Repository.MainCategoryRepository;
import com.example.cakeprj.Repository.OrderDetailsRepository;
import com.example.cakeprj.dto.request.SubCategoryCreationRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final CakeProductRepository cakeProductRepository;
    private final OrderService orderService;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, MainCategoryRepository mainCategoryRepository, CakeProductRepository cakeProductRepository, OrderService orderService, OrderDetailsRepository orderDetailsRepository) {
        this.categoryRepository = categoryRepository;
        this.mainCategoryRepository = mainCategoryRepository;
        this.cakeProductRepository = cakeProductRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderService = orderService;
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

    public List<Category> findAllByID(List<String> ids){
        return categoryRepository.findAllById(ids);
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

    @Transactional
    public void deleteSubcategory(String subCategoryID) {
        // Get the category
        Category category = categoryRepository.findById(subCategoryID)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Get all cakes in this category
        List<Cake> cakes = cakeProductRepository.findByCategoryID(subCategoryID);

        // Check for active orders
        for (Cake cake : cakes) {
            if (orderService.isCakeInActiveOrders(cake.getId())) {
                throw new IllegalStateException("Cannot delete category. A cake in this category is in active order.");
            }
        }

        // For each cake in this category
        for (Cake cake : cakes) {
            // Remove this category from the cake's categories
            cake.getCategories().remove(category);

            // If the cake has no other categories, prepare to delete it
            if (cake.getCategories().isEmpty()) {
                // Set all orderDetails references to this cake to null
                orderDetailsRepository.setCakeToNull(cake.getId());

                // Delete the cake directly with SQL to bypass Hibernate
                cakeProductRepository.deleteCakeDirectly(cake.getId());
            } else {
                // Otherwise just save the updated cake
                cakeProductRepository.save(cake);
            }
        }

        // Finally delete the category
        categoryRepository.deleteById(subCategoryID);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}