package com.example.cakeprj.Controller;

import com.example.cakeprj.Entity.Category;
import com.example.cakeprj.Entity.MainCategory;
import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.Repository.UserRepository;
import com.example.cakeprj.Security.CustomUserDetails;
import com.example.cakeprj.Service.CategoryService;
import com.example.cakeprj.Service.MainCategoryService;
import com.example.cakeprj.Service.UserService;
import com.example.cakeprj.dto.request.SubCategoryCreationRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("admin")
public class AdminPageController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final MainCategoryService mainCategoryService;

    public AdminPageController(UserRepository userRepository, UserService userService, CategoryService categoryService, MainCategoryService mainCategoryService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.mainCategoryService = mainCategoryService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Admin/admin-dashboard";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            model.addAttribute("admin", customUserDetails);
        }
        return "Admin/admin-profile";
    }

    @PostMapping("/updateprofile")
    public String updateProfile(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("email") String email,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model,
            RedirectAttributes redirectAttributes) {
        Users user = userDetails.getUser();

        try {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhoneNumber(mobileNumber);
            userService.updateUser(user);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("admin", user);
            return "Admin/admin-profile";
        }
        return "redirect:/admin/profile";

    }

    @GetMapping("/userlist")
    public String userlist(Model model) {
        List<Users> allUsers = userService.getAllUser();
        List<Users> usersOnly = allUsers.stream()
                .filter(user -> user.getRoles().stream().noneMatch(role -> "ADMIN".equals(role.getName())))
                .toList();

        model.addAttribute("users", usersOnly);
        return "Admin/register-user";
    }

    @GetMapping("/category/maincategory")
    public String mainCategory(Model model) {
        return "Admin/maincategory";
    }


    @PostMapping("/category/add-subcategory")
    public String addSubCategory(@RequestParam String maincategoryid,
                                 @RequestParam String subcategoryId,
                                 @RequestParam String subcategoryName,
                                 Model model) {


        boolean isAdded = categoryService.addSubCategory(maincategoryid, subcategoryId, subcategoryName);

        if (isAdded) {
            System.out.println("Subcategory added successfully.");
            model.addAttribute("message", "Subcategory added successfully!");
            model.addAttribute("addSuccessful", true);
        } else {
            System.out.println("Error: Subcategory ID already exists.");
            model.addAttribute("error", "Subcategory ID already exists!");
        }

        return "Admin/maincategory";
    }

    @GetMapping("/category/subcategory-list")
    public String subCategoryList(@RequestParam("id") String mainCategoryId, Model model) {
        List<SubCategoryCreationRequest> subCategoriesList = categoryService.getSubCategories(mainCategoryId);
        String mainCategoryName = mainCategoryService.getMainCategoryName(mainCategoryId)
                .map(MainCategory::getName)
                .orElse("Unknown Category");
        model.addAttribute("subcategories", subCategoriesList);
        model.addAttribute("mainCategoryName", mainCategoryName);
        model.addAttribute("mainCategoryId", mainCategoryId);
        return "Admin/subcategory-table";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable String id,
                                 @RequestParam("mainCategoryId") String mainCategoryId,
                                 RedirectAttributes redirectAttributes) {
        categoryService.deleteSubcategory(id);
        redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully!");
        return "redirect:/admin/category/subcategory-list?id=" + mainCategoryId;
    }

    @GetMapping("/category/edit/{categoryId}")
    public String editCategory(@PathVariable String categoryId,
                               @ModelAttribute("successMessage") String successMessage,
                               @ModelAttribute("errorMessage") String errorMessage,
                               Model model) {
        try {
            Category category = categoryService.getSubCategoryByID(categoryId);
            List<MainCategory> mainCategories = mainCategoryService.getAllMainCategory();

            int cakeCount = categoryService.getCakeCount(categoryId);

            model.addAttribute("category", category);
            model.addAttribute("mainCategories", mainCategories);
            model.addAttribute("selectedMainCategoryId", category.getMainCategory().getId());
            model.addAttribute("cakeCount", cakeCount);

            model.addAttribute("successMessage", successMessage);
            model.addAttribute("errorMessage", errorMessage);



            return "Admin/update-category";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Category not found: " + e.getMessage());
            return "redirect:/admin/category/maincategory";
        }
    }


    @PostMapping("/category/update-category")
    public String updateCategory(@RequestParam String categoryID,
                                 @RequestParam String categoryName,
                                 @RequestParam String mainCategory,
                                 RedirectAttributes redirectAttributes) {
        try {
            if(categoryID != null){
                categoryService.updateSubCategory(categoryID, categoryName, mainCategory);
                redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully!");
            }

            return "redirect:/admin/category/edit/" + categoryID;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update category: " + e.getMessage());
            return "redirect:/admin/category/edit/" + categoryID;
        }
    }



}
