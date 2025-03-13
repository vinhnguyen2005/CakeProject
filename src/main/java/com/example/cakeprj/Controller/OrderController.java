
package com.example.cakeprj.Controller;

import com.example.cakeprj.Entity.Order;
import com.example.cakeprj.Entity.OrderDetails;
import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.Service.OrderService;
import com.example.cakeprj.Service.UserService;
import com.example.cakeprj.util.PriceFormatter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/view-order")
    public String viewOrder(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        Users user = userService.findByUsername(userDetails.getUsername());
        List<Order> orderList = orderService.getOrders(user.getId());
        model.addAttribute("orders", orderList);
        model.addAttribute("user", user);
        return "view-order";
    }

    @GetMapping("/details/{id}")
    public String viewOrderDetails(@PathVariable UUID id, Model model) {
        List<OrderDetails> orderDetailsList = orderService.getOrderDetails(id);
        for (OrderDetails orderDetails : orderDetailsList) {
            orderDetails.setFormattedPrice(PriceFormatter.formatPrice((orderDetails.getPrice()) / 1000));
        }
        Order order = orderService.getOrder(id);
        order.setFormattedPrice(PriceFormatter.formatPrice(order.getTotalPrice() / 1000));
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetailsList);
        return "view-order-details";
    }
}
