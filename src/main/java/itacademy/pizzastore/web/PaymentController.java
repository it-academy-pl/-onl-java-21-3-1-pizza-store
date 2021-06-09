package itacademy.pizzastore.web;

import itacademy.pizzastore.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping("/pay/{id}")
    public String payForOrder(@PathVariable long id){
        paymentService.payForOrder(id);
        return String.format("You paid for order number %d.", id);
    }



}
