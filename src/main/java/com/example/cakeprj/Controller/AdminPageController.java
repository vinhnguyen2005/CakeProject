package com.example.cakeprj.Controller;

import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.Repository.UserRepository;
import com.example.cakeprj.Security.CustomUserDetails;
import com.example.cakeprj.Service.UserService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class AdminPageController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserService userService;

    public AdminPageController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
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
        System.out.println("Update profile request received: firstName=" + firstName + ", lastName=" + lastName);
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

}
