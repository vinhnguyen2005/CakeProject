package com.example.cakeprj.Controller;

import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.Repository.UserRepository;
import com.example.cakeprj.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {
    private final CartService cartService;
    private final UserRepository userRepository;

    @Autowired
    public GlobalController(CartService cartService, UserRepository userRepository) {
        this.cartService = cartService;
        this.userRepository = userRepository;
    }


    @ModelAttribute
    public void addUsernameToModel(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
    }

    @ModelAttribute("cartItemCount")
    public int getCartItemCount(@AuthenticationPrincipal UserDetails userDetail) {
        if (userDetail == null) {
            return 0;
        }

        Users user = userRepository.findByUsername(userDetail.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartService.countItemsFromCart(user.getId());
    }
}
