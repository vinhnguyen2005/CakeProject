package com.example.cakeprj.Controller;

import com.example.cakeprj.Entity.*;
import com.example.cakeprj.Repository.CakeProductRepository;
import com.example.cakeprj.Repository.UserRepository;
import com.example.cakeprj.Security.CustomUserDetails;
import com.example.cakeprj.Service.*;
import com.example.cakeprj.dto.request.SubCategoryCreationRequest;
import com.example.cakeprj.util.PriceFormatter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Controller
@RequestMapping("admin")
public class AdminPageController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final MainCategoryService mainCategoryService;
    private final CartService cartService;
    private final CakeService cakeService;
    private final CakeProductRepository cakeProductRepository;
    private final OrderService orderService;
    private final OnlineUserService onlineUserService;

    public AdminPageController(UserRepository userRepository, OnlineUserService onlineUserService,UserService userService, CategoryService categoryService, MainCategoryService mainCategoryService, CartService cartService, CakeService cakeService, CakeProductRepository cakeProductRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.mainCategoryService = mainCategoryService;
        this.cartService = cartService;
        this.cakeService = cakeService;
        this.cakeProductRepository = cakeProductRepository;
        this.orderService = orderService;
        this.onlineUserService = onlineUserService;
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

    @GetMapping("/online-subcriber")
    public String showOnlineSubcriber(Model model) {
        List<OnlineUsers> onlineUsersList = onlineUserService.getAllOnlineUsers();
        model.addAttribute("onlineUsersList", onlineUsersList);
        return "Admin/onlinesubcriber";
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
            if (categoryID != null) {
                categoryService.updateSubCategory(categoryID, categoryName, mainCategory);
                redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully!");
            }

            return "redirect:/admin/category/edit/" + categoryID;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update category: " + e.getMessage());
            return "redirect:/admin/category/edit/" + categoryID;
        }
    }

    @GetMapping("/cake/add")
    public String showAddCakeForm(@RequestParam(value = "successMessage", required = false) String successMessage,
                                  @RequestParam(value = "errorMessage", required = false) String errorMessage,
                                  Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        if (successMessage != null) model.addAttribute("successMessage", successMessage);
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);

        return "Admin/add-cake";
    }

    @PostMapping("/cake/add")
    public String addCake(
            @RequestParam(value = "categoryIds") List<String> categoryIds,
            @RequestParam("categoryId") String categoryId,
            @RequestParam("cakeNumber") String cakeNumber,
            @RequestParam("cakeName") String cakeName,
            @RequestParam("cakeImage") MultipartFile file,
            @RequestParam(value = "withSize", defaultValue = "false") boolean withSize,
            @RequestParam("cakePrice") double cakePrice,
            RedirectAttributes redirectAttributes) throws IOException {

        if (categoryId == null || categoryId.isEmpty() || cakeNumber == null || cakeNumber.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Category and Cake Number are required!");
            return "redirect:/admin/cake/add";
        }

        String cakeId = categoryId.toUpperCase() + cakeNumber;
        if (cakeService.existById(cakeId)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cake already exists!");
            return "redirect:/admin/cake/add";
        }

        Cake newCake = new Cake();
        newCake.setId(cakeId);
        newCake.setName(cakeName);
        newCake.setHasSize(withSize);
        newCake.setPrice(cakePrice);

        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads/");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            newCake.setImageURL(fileName);
        }

        if (categoryIds != null) {
            Set<Category> selectedCategories = new HashSet<>(categoryService.findAllByID(categoryIds));
            newCake.setCategories(selectedCategories);
        }

        cakeProductRepository.save(newCake);

        redirectAttributes.addFlashAttribute("successMessage", "Cake added successfully!");
        return "redirect:/admin/cake/add";
    }

    @GetMapping("/cake/manage")
    public String showManageCakeTable(@ModelAttribute("deleteSuccessful") String deleteSuccessful, @ModelAttribute("errorMessage") String errorMessage, Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<Cake> cakes = cakeService.getAllCakes();
        model.addAttribute("cakes", cakes);
        for (Cake cake : cakes) {
            cake.setFormattedPrice(PriceFormatter.formatPrice(cake.getPrice()));
        }
        model.addAttribute("deleteSuccessful", deleteSuccessful);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("categories", categories);
        return "Admin/cake-table";
    }

    @GetMapping("/cake/delete/{id}")
    public String deleteCake(@PathVariable String id, RedirectAttributes redirectAttributes) {
        cakeService.deleteById(id);
        redirectAttributes.addFlashAttribute("deleteSuccessful", "Cake deleted successfully!");
        return "redirect:/admin/cake/manage";
    }

    @GetMapping("/cake/update/{id}")
    public String updateCake(@PathVariable String id, Model model,
                             @ModelAttribute("errorMessage") String errorMessage,
                             @ModelAttribute("successMessage") String successMessage,
                             RedirectAttributes redirectAttributes) {
        Cake foundCake = cakeService.getCakeById(id);

        if (foundCake == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cake not found!");
            return "redirect:/admin/cake/manage";
        }

        model.addAttribute("cake", foundCake);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("successMessage", successMessage);
        return "Admin/update-cake";
    }

    @PostMapping("/cake/update/{id}")
    public String updateCake(@PathVariable String id,
                             @RequestParam("cakeName") String name,
                             @RequestParam("cakePrice") Double price,
                             @RequestParam(value = "cakeImage", required = false) MultipartFile imageFile,
                             @RequestParam("withSize") Boolean hasSize,
                             RedirectAttributes redirectAttributes) {
        try {
            Cake foundCake = cakeService.getCakeById(id);
            foundCake.setName(name);
            foundCake.setPrice(price);
            foundCake.setHasSize(hasSize);

            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("uploads/");

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                foundCake.setImageURL(fileName);
            }

            cakeService.updateCake(foundCake);
            redirectAttributes.addFlashAttribute("successMessage", "Cake updated successfully!");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error uploading file: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating cake: " + e.getMessage());
        }

        return "redirect:/admin/cake/update/" + id;
    }

    @GetMapping("/orders")
    public String showOrders(@RequestParam(required = false) OrderStatus status, Model model) {
        List<Order> orders;
        if (status == null) {
            orders = orderService.getAllOrders();
        } else {
            orders = orderService.getOrderByOrderStatus(status);
        }
        for (Order order : orders) {
            order.setFormattedPrice(PriceFormatter.formatPrice(order.getTotalPrice() / 1000));
        }

        List<OrderStatus> orderStatuses = orderService.getAllStatuses();
        System.out.println("Received status: " + status);
        System.out.println("Order Status List: " + orderStatuses);

        model.addAttribute("orders", orders);
        model.addAttribute("orderStatuses", orderStatuses);
        assert status != null;
        model.addAttribute("selectedStatus", status);

        return "Admin/admin-order-table";
    }

    @GetMapping("/order/view/{id}")
    public String showOrderDetails(@PathVariable UUID id, Model model) {
        Order foundOrder = orderService.getOrder(id);
        List<OrderDetails> orderDetails = orderService.getOrderDetails(id);
        List<OrderStatus> orderStatuses = orderService.getAllStatuses();
        for(OrderDetails orderDetail : orderDetails) {
            orderDetail.setFormattedPrice(PriceFormatter.formatPrice(orderDetail.getPrice() / 1000));
        }
        foundOrder.setFormattedPrice(PriceFormatter.formatPrice(foundOrder.getTotalPrice() / 1000));
        model.addAttribute("order", foundOrder);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("orderStatuses", orderStatuses);
        return "Admin/admin-order-details";
    }

    @PostMapping("/updateOrderStatus")
    public String updateOrderStatus(@RequestParam("orderId") UUID orderId,
                                    @RequestParam("status") OrderStatus status,
                                    RedirectAttributes redirectAttributes) {
        try {
            System.out.println("🟢 Status received: " + status.name());

            Order foundOrder = orderService.getOrder(orderId);
            if (foundOrder != null) {
                foundOrder.setStatus(status);
                orderService.updateOrder(foundOrder);
            }
            redirectAttributes.addFlashAttribute("success", "Order status has been changed!");
            return "redirect:/admin/order/view/" + orderId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating order status: " + e.getMessage());
            return "redirect:/admin/order/view/" + orderId;
        }
    }



}
