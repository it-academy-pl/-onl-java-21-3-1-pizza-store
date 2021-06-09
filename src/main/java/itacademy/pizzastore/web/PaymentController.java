package itacademy.pizzastore.web;

import itacademy.pizzastore.HelloSpringWeb;
import itacademy.pizzastore.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/pay")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("pay/{id}")
    public PaymentResponse letPayForOrder(@PathVariable long id) {
        return new PaymentResponse("Please, pay for order " + id + ".");
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private static class PaymentResponse {
        private String message;
    }


}
