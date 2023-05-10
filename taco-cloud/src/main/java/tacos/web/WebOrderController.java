package tacos.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.entity.Order;
import tacos.entity.User;
import tacos.messaging.OrderJmsSender;
import tacos.service.OrderService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@RequiredArgsConstructor
public class WebOrderController {

    private final OrderService orderService;

    private final OrderJmsSender orderJmsSender;

    @GetMapping("/current")
    public String orderForm(Order order, @AuthenticationPrincipal User user){
        order.setDeliveryName(user.getFullName());
        order.setDeliveryCity(user.getCity());
        order.setDeliveryState(user.getState());
        order.setDeliveryStreet(user.getStreet());
        order.setDeliveryZip(user.getZip());

        return "orderForm";
    }


    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user){
        if (errors.hasErrors()){
            return "orderForm";
        }

        order.setUser(user);
        orderService.save(order);
        orderJmsSender.sendOrder(order);

        sessionStatus.setComplete();

        return "redirect:/";
    }
}
