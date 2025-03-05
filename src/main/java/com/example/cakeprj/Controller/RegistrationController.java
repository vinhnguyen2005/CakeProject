package com.example.cakeprj.Controller;

import com.example.cakeprj.Service.UserService;
import com.example.cakeprj.dto.request.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String registerUser(@ModelAttribute("user") UserCreationRequest user, Model model) {
        try {
            userService.save(user);
            model.addAttribute("successfulMessage", "User registered successfully!");
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("failedMessage", e.getMessage());
            return "register";
        }
    }
}
