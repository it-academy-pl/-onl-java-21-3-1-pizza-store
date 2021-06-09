package itacademy.pizzastore.web;

import itacademy.pizzastore.domain.*;
import itacademy.pizzastore.service.OrderService;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderList")
    public List<OrderResponse> getAvailableOrders(){
        return orderService.create().stream()
                .map(OrderResponse::from)
                .collect(Collectors.toList());
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

        public static OrderResponse from(Order order){
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

    @GetMapping("/provideAddress/{id}")
    public Status orderAddress(@PathVariable long orderId, Address address){
        return orderService.provideDeliveryAddress(orderId, address);
    }

    @GetMapping("/cancel/{id}")
    public Status orderCancel(@PathVariable long orderId){
        return orderService.cancel(orderId);
    }

    @GetMapping("/status/{id}")
    public Status orderStatus(@PathVariable long orderId){
        return orderService.getStatusForOrder(orderId);
    }

    @GetMapping("/rate/{id}")
    public Status orderRate(@PathVariable long orderId, Rating rating){
        return orderService.rateOrder(orderId, Rating rating);
    }



}
