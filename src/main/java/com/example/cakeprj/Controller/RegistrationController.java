package com.example.cakeprj.Controller;

import com.example.cakeprj.Service.UserService;
import com.example.cakeprj.dto.request.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserCreationRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserCreationRequest user, RedirectAttributes redirectAttributes) {
        try {
            userService.save(user);
            redirectAttributes.addFlashAttribute("successfulMessage", "User registered successfully!");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("failedMessage", e.getMessage());
            return "redirect:/register";
        }
    }
}
