package com.example.cakeprj.Controller;

import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.Service.PasswordResetTokenService;
import com.example.cakeprj.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/password")
public class PasswordResetController {
    private final PasswordResetTokenService passwordResetTokenService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PasswordResetController(PasswordResetTokenService passwordResetTokenService, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passwordResetTokenService = passwordResetTokenService;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/request-reset")
    public String requestReset(Model model) {
        return "reset-request";
    }

    @PostMapping("/request-reset")
    public String requestReset(@RequestParam String email, Model model) {
        boolean success = passwordResetTokenService.generatePasswordResetToken(email);
        if (success) {
            model.addAttribute("message", "Email đặt lại mật khẩu đã được gửi. Hãy kiểm tra hộp thư của bạn!");
        } else {
            model.addAttribute("error", "Email không tồn tại trong hệ thống!");
        }
        return "reset-request";
    }

    @GetMapping("/reset")
    public String showResetForm(@RequestParam String token, Model model) {
        boolean isValid = passwordResetTokenService.validatePasswordResetToken(token);
        if (!isValid) {
            model.addAttribute("error", "Token không hợp lệ hoặc đã hết hạn!");
            return "reset-request";
        }
        model.addAttribute("token", token);
        return "reset-form";
    }

    @PostMapping("/reset")
    public String resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (!passwordResetTokenService.validatePasswordResetToken(token)) {
            model.addAttribute("error", "Token không hợp lệ hoặc đã hết hạn!");
            return "reset-request";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp!");
            model.addAttribute("token", token);
            return "reset-request";
        }

        Users user = passwordResetTokenService.getUserByToken(token);
        if (user == null) {
            model.addAttribute("error", "Không tìm thấy người dùng!");
            return "reset-request";
        }
        if (bCryptPasswordEncoder.matches(newPassword, user.getPassword())) {
            model.addAttribute("error", "Mật khẩu mới phải khác với mật khẩu cũ!");
            return "reset-request";
        }

        String hashedPassword = bCryptPasswordEncoder.encode(newPassword);
        userService.updateUserPassword(user.getId(), hashedPassword);
        redirectAttributes.addFlashAttribute("message", "Mật khẩu đã được đặt lại thành công! Vui lòng đăng nhập.");
        return "redirect:/login";
    }







}
