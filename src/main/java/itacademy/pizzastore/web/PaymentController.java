package itacademy.pizzastore.web;

import itacademy.pizzastore.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("pay/{id}")
    public ResponseEntity<Void> letPayForOrder(@PathVariable long id) {
        paymentService.payForOrder(id);
        return ResponseEntity.ok().build();
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private static class PaymentResponse {
        private String message;
    }

}
