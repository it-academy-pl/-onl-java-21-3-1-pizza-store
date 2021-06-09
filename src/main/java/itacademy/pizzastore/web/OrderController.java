package itacademy.pizzastore.web;

import itacademy.pizzastore.domain.Status;
import itacademy.pizzastore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @GetMapping("/status/{id}")
    public Status orderStatus(@PathVariable("id") long orderId){
        return orderService.getStatusForOrder(orderId);
    }

    @GetMapping("/cancel/{id}")
    public Status cancelOrder(@PathVariable("id") long orderId){
        return orderService.cancel(orderId);
    }

}
