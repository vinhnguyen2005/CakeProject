package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.Cart;
import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.Repository.CartRepository;
import com.example.cakeprj.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SessionScope
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public void addCart(Cart cart, UUID userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        cart.setUser(user);

        Optional<Cart> cartOptional = cartRepository.findByNameAndSizeAndUser(
                cart.getName(), cart.getSize(), user);

        if (cartOptional.isPresent()) {
            Cart existingCart = cartOptional.get();
            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
            cartRepository.save(existingCart);
        } else {
            cartRepository.save(cart);
        }
    }

    public int countItemsFromCart(UUID userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return getCarts(userId).stream().mapToInt(Cart::getQuantity).sum();
    }

    public void removeCart(UUID cartId) {
        cartRepository.deleteById(cartId);
    }

    public List<Cart> getCarts(UUID userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user);
    }

    public void clearCart(UUID userId){
        List<Cart> carts = getCarts(userId);
        cartRepository.deleteAll(carts);
    }

    public Cart getCartItemById(UUID cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    public void updateCart(Cart cart){
        cartRepository.save(cart);
    }

}