package com.example.cakeprj.Controller;

import com.example.cakeprj.Entity.Cake;
import com.example.cakeprj.Service.CakeService;
import com.example.cakeprj.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CakeController {

    @Autowired
    private CakeService cakeService;

    @Autowired
    private CategoryService categoryService;

    public String formatPrice(double price) {
        double adjustedPrice = price * 1000;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);

        return formatter.format(adjustedPrice);
    }

    private void populateCakeModel(Model model, String categoryId, int page, String viewName) {
        int pageSize = 15;
        int startIndex = (page - 1) * pageSize;

        List<Cake> cakeList = cakeService.getTopCakesByCategory(categoryId, pageSize, startIndex);
        for (Cake cake : cakeList) {
            cake.setFormattedPrice(formatPrice(cake.getPrice()));
        }

        String categoryName = categoryService.getCategoryName(categoryId);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("cakelist", cakeList);
        model.addAttribute("currentPage", page);

        int totalPages = cakeService.getTotalPages(categoryId, pageSize);
        model.addAttribute("totalPages", totalPages);
    }

    @GetMapping("/home")
    public String getAllHomeCakes(Model model) {
        String defaultCategoryId = "GT";
        List<Cake> homepageCake = cakeService.getTopCakesByCategory(defaultCategoryId, 8, 0);
        for (Cake cake : homepageCake) {
            cake.setFormattedPrice(formatPrice(cake.getPrice()));
        }
        model.addAttribute("homepagecakes", homepageCake);


        return "home";
    }

    @GetMapping("/details/{id}")
    public String getDetailCake(@PathVariable String id, Model model) {
        Cake cake = cakeService.getCakeById(id);
        cake.setFormattedPrice(formatPrice(cake.getPrice()));
        Map<String, String> pricewithsize = new LinkedHashMap<>();
        pricewithsize.put("16 cm", formatPrice(cake.getPrice()));
        pricewithsize.put("18 cm", formatPrice(cake.getPrice() + 50));
        pricewithsize.put("20 cm", formatPrice(cake.getPrice() + 100));
        pricewithsize.put("22 cm", formatPrice(cake.getPrice() + 150));
        pricewithsize.put("24 cm", formatPrice(cake.getPrice() + 250));
        pricewithsize.put("26 cm", formatPrice(cake.getPrice() + 350));

        model.addAttribute("cakedetails", cake);
        model.addAttribute("priceWithSize", pricewithsize);
        return "details";
    }


    @GetMapping("/gateux/{categoryId}")
    public String gateuxPage(@PathVariable String categoryId, @RequestParam(defaultValue = "1") int page, Model model) {
        populateCakeModel(model, categoryId, page, "gateux");
        return "gateux";
    }

    @GetMapping("/banhman-minicake/{categoryId}")
    public String banhmanPage(@PathVariable String categoryId, @RequestParam(defaultValue = "1") int page, Model model) {
        populateCakeModel(model, categoryId, page, "banhman");
        return "banhman";
    }

    @GetMapping("/loai-khac/{categoryId}")
    public String banhkhacPage(@PathVariable String categoryId, @RequestParam(defaultValue = "1") int page, Model model) {
        populateCakeModel(model, categoryId, page, "banhkhac");
        return "banhkhac";
    }
}
