package com.example.cakeprj.Controller;

import com.example.cakeprj.Entity.Cart;
import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.Service.CartService;
import com.example.cakeprj.Repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final UserRepository userRepository;

    public CartController(CartService cartService, UserRepository userRepository) {
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showCart(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Users user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("cartItems", cartService.getCarts(user.getId()));
        return "cart";
    }

    @PostMapping("/process-cart")
    public String processCart(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String imageURL,
                              @RequestParam String size,
                              @RequestParam int quantity,
                              @RequestParam double price,
                              @RequestParam(required = false, defaultValue = "false") boolean redirectToCart) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Users user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = new Cart();
        cart.setCakeID(id);
        cart.setName(name);
        cart.setImageURL(imageURL);
        cart.setSize(size);
        cart.setQuantity(quantity);
        cart.setPrice(price);
        cart.setUser(user);

        cartService.addCart(cart, user.getId());

        return redirectToCart ? "redirect:/cart" : "redirect:/";
    }

    @GetMapping("/remove")
    public String removeCartItem(@AuthenticationPrincipal UserDetails userDetails, @RequestParam UUID id) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        cartService.removeCart(id);
        return "redirect:/cart";
    }
}