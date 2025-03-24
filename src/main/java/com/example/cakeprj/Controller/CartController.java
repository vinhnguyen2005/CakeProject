package com.example.cakeprj.Controller;

import com.example.cakeprj.Entity.*;
import com.example.cakeprj.Repository.UserRepository;
import com.example.cakeprj.Service.CakeService;
import com.example.cakeprj.Service.CartService;
import com.example.cakeprj.Service.OrderService;
import com.example.cakeprj.Service.UserService;
import com.example.cakeprj.util.PriceFormatter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;
    private final CakeService cakeService;
    private final UserService userService;

    public CartController(CartService cartService, UserRepository userRepository, OrderService orderService, CakeService cakeService, UserService userService) {
        this.cartService = cartService;
        this.cakeService = cakeService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String showCart(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Users user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("cartItems", cartService.getCarts(user.getId()));
        return "cart";
    }

    @PostMapping("/update")
    public String updateCart(
            @RequestParam("itemIds") List<UUID> itemIds,
            @RequestParam("quantities") List<Integer> quantities,
            @RequestParam("totalOrderPrice") double totalOrderPrice) {

        for (int i = 0; i < itemIds.size(); i++) {
            UUID itemId = itemIds.get(i);
            int quantity = quantities.get(i);

            Cart cartItem = cartService.getCartItemById(itemId);

            if (cartItem != null) {
                cartItem.setQuantity(quantity);
                double updatedPrice = cartItem.getUnitprice() * quantity;
                cartItem.setPrice(updatedPrice);

                cartService.updateCart(cartItem);
            }
        }

        return "redirect:/cart";
    }




    @PostMapping("/process-cart")
    public String processCart(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String imageURL,
                              @RequestParam String size,
                              @RequestParam int quantity,
                              @RequestParam double price,
                              @RequestParam double unitPrice,
                              @RequestParam(required = false, defaultValue = "false") boolean redirectToCart) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Users user = userService.findByUsername(userDetails.getUsername());

        Cart cart = new Cart(id, name, imageURL, size, quantity, price, user, unitPrice);
        cartService.addCart(cart, user.getId());

        return redirectToCart ? "redirect:/cart" : "redirect:/details/" + id;
    }

    @GetMapping("/remove")
    public String removeCartItem(@AuthenticationPrincipal UserDetails userDetails, @RequestParam UUID id) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        cartService.removeCart(id);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String showCheckOutPage(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("successMessage") String successMessage ,Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Users user = userService.findByUsername(userDetails.getUsername());

        List<Cart> cartItems = cartService.getCarts(user.getId());
        if(cartItems.isEmpty()){
            return "redirect:/cart";
        }
        double totalPrice = 0;
        for (Cart cartItem : cartItems) {
            cartItem.setFormattedPrice(PriceFormatter.formatPrice(cartItem.getUnitprice() * cartItem.getQuantity()));
            totalPrice += cartItem.getPrice();
        }

        model.addAttribute("totalPrice", PriceFormatter.formatPrice(totalPrice));
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("user", user);
        model.addAttribute("successMessage", successMessage);

        model.addAttribute("order", new Order());
        return "checkout";
    }

    @PostMapping("/checkout/process")
    public String processCheckOut(@AuthenticationPrincipal UserDetails userDetails,
                                  @ModelAttribute Order order,
                                  @RequestParam List<String> cakeIds,
                                  @RequestParam List<Integer> quantities,
                                  @RequestParam List<String> sizes,
                                  @RequestParam List<Double> prices,
                                  RedirectAttributes redirectAttributes) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Users user = userService.findByUsername(userDetails.getUsername());
        List<Cart> cartItems = cartService.getCarts(user.getId());

        order.setUser(user);
        System.out.println("Debug: Danh sách giá từng bánh: " + prices);

        double totalPrice = 0;
        for (Double price : prices) {
            totalPrice += price;
        }

        System.out.println("Debug: Tổng giá (totalPrice) = " + totalPrice);
        order.setTotalPrice(totalPrice);
        orderService.save(order);

        Set<OrderDetails> orderDetailsList = new HashSet<>();
        for (int i = 0; i < cakeIds.size(); i++) {
            OrderDetails orderDetails = new OrderDetails();
            Cake cakeFound = cakeService.getCakeById(cakeIds.get(i));
            orderDetails.setCake(cakeFound);
            orderDetails.setOrder(order);
            orderDetails.setQuantity(quantities.get(i));
            orderDetails.setPrice(prices.get(i));
            if (cakeFound.getHasSize()) {
                String size = (i < sizes.size()) ? sizes.get(i) : "";
                orderDetails.setSize(size != null ? size.trim() : "");
            } else {
                orderDetails.setSize("");
            }
            orderDetailsList.add(orderDetails);
        }
        order.setOrderDetails(orderDetailsList);

        orderService.save(order);

        cartService.clearCart(user.getId());
        redirectAttributes.addFlashAttribute("successMessage", "Order has been processed successfully!");

        return "redirect:/order/view-order";
    }
}
