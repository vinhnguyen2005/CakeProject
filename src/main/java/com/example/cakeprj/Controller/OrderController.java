
package com.example.cakeprj.Controller;

import com.example.cakeprj.Entity.Order;
import com.example.cakeprj.Entity.OrderDetails;
import com.example.cakeprj.Entity.OrderStatus;
import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.Service.OrderService;
import com.example.cakeprj.Service.UserService;
import com.example.cakeprj.util.PriceFormatter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/view-order")
    public String viewOrder(@AuthenticationPrincipal UserDetails userDetails,
                            @RequestParam(value = "status", required = false, defaultValue = "default") String status,
                            Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Users user = userService.findByUsername(userDetails.getUsername());
        List<Order> orderList = orderService.getOrders(user.getId());

        if (!"all".equals(status)) {
            orderList = orderList.stream()
                    .filter(order -> order.getStatus() != OrderStatus.DA_GIAO
                            && order.getStatus() != OrderStatus.DA_HUY)
                    .toList();
        }

        model.addAttribute("orders", orderList);
        model.addAttribute("user", user);
        model.addAttribute("status", status);

        return "view-order";
    }


    @PostMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable UUID id, Model model) {
        Order order = orderService.getOrder(id);
        if (order != null) {
            order.setStatus(OrderStatus.DA_HUY);
            orderService.updateOrder(order);
        }
        return "redirect:/order/view-order";
    }

}
