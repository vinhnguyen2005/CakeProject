package com.example.cakeprj.Controller;

import com.example.cakeprj.Entity.Cake;
import com.example.cakeprj.Entity.Category;
import com.example.cakeprj.Service.CakeService;
import com.example.cakeprj.Service.CategoryService;
import com.example.cakeprj.util.PriceFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CakeController {
    private final CakeService cakeService;
    private final CategoryService categoryService;

    @Autowired
    public CakeController(CakeService cakeService, CategoryService categoryService) {
        this.cakeService = cakeService;
        this.categoryService = categoryService;
    }

    private void populateCakeModel(Model model, String categoryId, int page, String viewName) {
        int pageSize = 15;
        int startIndex = (page - 1) * pageSize;

        List<Cake> cakeList = cakeService.getTopCakesByCategory(categoryId, pageSize, startIndex);
        for (Cake cake : cakeList) {
            cake.setFormattedPrice(PriceFormatter.formatPrice(cake.getPrice()));
        }

        String categoryName = categoryService.getCategoryName(categoryId);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("cakelist", cakeList);
        model.addAttribute("currentPage", page);

        int totalPages = cakeService.getTotalPages(categoryId, pageSize);
        model.addAttribute("totalPages", totalPages);
    }

    private List<Category> getSortedSubcategories(String mainCategoryId) {
        List<Category> subcategories = categoryService.getCategoriesByMainCategory(mainCategoryId);

        List<String> desiredOrder = Arrays.asList("gt", "ms", "bpk", "vlt", "bbtg", "bia", "bv", "bsk");

        subcategories.sort(Comparator.comparingInt(cat -> desiredOrder.indexOf(cat.getId())));

        return subcategories;
    }

    @GetMapping("/home")
    public String getAllHomeCakes(Model model) {
        String defaultCategoryId = "GT";
        List<Cake> homepageCake = cakeService.getTopCakesByCategory(defaultCategoryId, 8, 0);
        for (Cake cake : homepageCake) {
            cake.setFormattedPrice(PriceFormatter.formatPrice(cake.getPrice()));
        }
        model.addAttribute("homepagecakes", homepageCake);

        return "home";
    }

    @GetMapping("/details/{id}")
    public String getDetailCake(@PathVariable String id, Model model) {
        Cake cake = cakeService.getCakeById(id);
        cake.setFormattedPrice(PriceFormatter.formatPrice(cake.getPrice()));

        Map<String, String> priceWithSize = new LinkedHashMap<>();

        if (cake.getHasSize()) {
            priceWithSize.put("16 cm", PriceFormatter.formatPrice(cake.getPrice()));
            priceWithSize.put("18 cm", PriceFormatter.formatPrice(cake.getPrice() + 50));
            priceWithSize.put("20 cm", PriceFormatter.formatPrice(cake.getPrice() + 100));
            priceWithSize.put("22 cm", PriceFormatter.formatPrice(cake.getPrice() + 150));
            priceWithSize.put("24 cm", PriceFormatter.formatPrice(cake.getPrice() + 250));
            priceWithSize.put("26 cm", PriceFormatter.formatPrice(cake.getPrice() + 350));
        } else {
            priceWithSize.put("16 cm", PriceFormatter.formatPrice(cake.getPrice()));
        }

        model.addAttribute("cakedetails", cake);
        model.addAttribute("priceWithSize", priceWithSize);
        return "details";
    }


    @GetMapping("/gateux/{categoryId}")
    public String gateuxPage(@PathVariable String categoryId, @RequestParam(defaultValue = "1") int page, Model model) {
        populateCakeModel(model, categoryId, page, "gateux");

        List<Category> subcategories = getSortedSubcategories("1");
        model.addAttribute("subcategories", subcategories);

        return "gateux";
    }

    @GetMapping("/banhman-minicake/{categoryId}")
    public String banhmanPage(@PathVariable String categoryId, @RequestParam(defaultValue = "1") int page, Model model) {
        populateCakeModel(model, categoryId, page, "banhman");

        List<Category> subcategories = getSortedSubcategories("2");
        model.addAttribute("subcategories", subcategories);

        return "banhman";
    }

    @GetMapping("/loai-khac/{categoryId}")
    public String banhkhacPage(@PathVariable String categoryId, @RequestParam(defaultValue = "1") int page, Model model) {
        populateCakeModel(model, categoryId, page, "banhkhac");

        List<Category> subcategories = getSortedSubcategories("3");
        model.addAttribute("subcategories", subcategories);

        return "banhkhac";
    }
}
