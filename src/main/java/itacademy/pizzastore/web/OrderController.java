package itacademy.pizzastore.web;

import itacademy.pizzastore.domain.*;
import itacademy.pizzastore.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody List<Pizza> pizzas) {
        return OrderResponse.from(orderService.create(pizzas));
    }

    @PutMapping("/{id}/address")
    public OrderResponse orderAddress(@PathVariable("id") long orderId, @RequestBody Address address) {
        return OrderResponse.from(orderService.provideDeliveryAddress(orderId, address));
    }

    @DeleteMapping("/{id}")
    public Status orderCancel(@PathVariable("id") long orderId) {
        return orderService.cancel(orderId);
    }

    @GetMapping("/{id}/status")
    public Status orderStatus(@PathVariable("id") long orderId) {
        return orderService.getStatusForOrder(orderId);
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<Void> orderRate(@PathVariable("id") long orderId, @RequestBody Rating rating) {
        orderService.rateOrder(orderId, rating);
        return ResponseEntity.ok().build();
    }

    @Value
    private static class OrderResponse {
        private static long lastId = 0;
        long id;
        List<Pizza> pizzas;
        Address deliveryAddress;
        String phoneNumber;
        BigDecimal totalPrice;
        Status status;
        int deliveryTimeInMinutes;
        PaymentType paymentType;

        public static OrderResponse from(Order order) {
            return new OrderResponse(
                    order.getId(),
                    order.getPizzas(),
                    order.getDeliveryAddress(),
                    order.getPhoneNumber(),
                    order.getTotalPrice(),
                    order.getStatus(),
                    order.getDeliveryTimeInMinutes(),
                    order.getPaymentType()
            );
        }
    }
}