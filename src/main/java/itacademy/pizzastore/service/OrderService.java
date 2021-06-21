package itacademy.pizzastore.service;

import itacademy.pizzastore.domain.*;
import itacademy.pizzastore.repository.OrderRepository;
import itacademy.pizzastore.service.exceptions.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//TODO: Check all the places for corner cases and throw an exceptions for all of them
//TODO: add tests for the cases
public class OrderService {

    private final OrderRepository orderRepository;

    public Order create(List<Pizza> pizzas) {
        BigDecimal totalPrice = pizzas.stream()
                .map(Pizza::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
      
        var newOrder = new Order(pizzas, null, "", totalPrice, Status.NEW, 0, null, null);
        orderRepository.save(newOrder);
        return newOrder;
    }

    public Order provideDeliveryAddress(long orderId, Address deliveryAddress) {
        var order = orderRepository.getById(orderId);
        order.setDeliveryAddress(deliveryAddress);
        orderRepository.save(order);
        return order;
    }

    public Order choosePaymentType(long orderId, PaymentType paymentType) {
        var order = orderRepository.getById(orderId);
        order.setPaymentType(paymentType);
        orderRepository.save(order);
        return order;
    }

    public Status cancel(long orderId) {
        var order = orderRepository.getById(orderId);
        order.setStatus(Status.CANCELLED);
        orderRepository.save(order);
        return order.getStatus();
    }

    public Status getStatusForOrder(long orderId) {
        return Optional.ofNullable(orderRepository.getById(orderId))
                .map(Order::getStatus)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID:" + orderId + " not found!"));
    }

    public Order provideDeliveryTime(long orderId, int deliveryTime) {
        var order = orderRepository.getById(orderId);
        order.setDeliveryTimeInMinutes(deliveryTime);
        orderRepository.save(order);
        return order;
    }

    public Order rateOrder(long orderId, Rating rating) {
        var order = orderRepository.getById(orderId);
        order.setRating(rating);
        orderRepository.save(order);
        return order;
    }
}
