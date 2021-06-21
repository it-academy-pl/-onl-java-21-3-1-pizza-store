package itacademy.pizzastore.web;

import itacademy.pizzastore.domain.*;
import itacademy.pizzastore.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
//TODO: validate client's requests where applicable and if something wrong - return error response immediately!
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody List<Pizza> pizzas) {
        if(pizzas == null || pizzas.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        //TODO: fix the compilation error
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(OrderResponse.from(orderService.create(pizzas)));
    }

    @PutMapping("/{id}/address")
//    public OrderResponse orderAddress(@PathVariable("id") long orderId, @RequestBody Address address) {
//        return OrderResponse.from(orderService.provideDeliveryAddress(orderId, address));
    public ResponseEntity<OrderResponse> orderAddress(@PathVariable("id") long orderId, @RequestBody Address address) {
        if(address == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(OrderResponse.from(orderService.provideDeliveryAddress(orderId, address)));
    }

    @DeleteMapping("/{id}/cancel")
    public Status orderCancel(@PathVariable("id") long orderId) {
        return orderService.cancel(orderId);
    }

    @GetMapping("/{id}/status")
//    public Status orderStatus(@PathVariable("id") long orderId) {
//        return orderService.getStatusForOrder(orderId);
    public ResponseEntity<Status> orderStatus(@PathVariable("id") long orderId) {
        if (orderService.getStatusForOrder(orderId) == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.getStatusForOrder(orderId));
    }

    @PostMapping("/{id}/rate")
        public ResponseEntity<Void> orderRate(@PathVariable("id") long orderId, @RequestBody Rating rating) {
        if(rating == null) {
            return ResponseEntity.noContent().build();
        }

        orderService.rateOrder(orderId, rating);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/deliveryTime/{deliveryTime}")
    public ResponseEntity<Void> deliveryTime(@PathVariable("id") long orderId, @PathVariable("deliveryTime") int deliveryTime) {
        if(deliveryTime < 0) {
            return ResponseEntity.badRequest().build();
        }

        orderService.provideDeliveryTime(orderId, deliveryTime);
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
