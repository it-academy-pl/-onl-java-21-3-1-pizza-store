package itacademy.pizzastore.service;

import itacademy.pizzastore.domain.*;
import itacademy.pizzastore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order create(List<Pizza> pizzas) {
        BigDecimal totalPrice = pizzas.stream()
                .map(Pizza::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
      
        var newOrder = new Order(pizzas, null, "", totalPrice, Status.NEW, 0, null);
        orderRepository.save(newOrder);
        return newOrder;
    }

    public Order provideDeliveryAddress(long orderId, Address deliveryAddress) {
        (String city) -> System.out.print(city);
        (String street) -> System.out.print(street);
        (String hoseNumber) -> System.out.print(hoseNumber);
        (String flatNumber) -> System.out.print(flatNumber);
    }

    public Order choosePaymentType(long orderId, PaymentType paymentType) {
        PaymentType allPaymentTypes [] = PaymentType.values();
        for (PaymentType p: allPaymentTypes)
            System.out.println(p);
        System.out.println();

        PaymentType pt = PaymentType.valueOf("CASH");
                return pt;
    }

    public Status cancel(long orderId) {
        var status = orderRepository.getById(orderId);
        status.setStatus(Status.CANCEL);
        orderRepository.save(status);
        return status;
    }

    public Status getStatusForOrder(long orderId) {
        Status allStatuses [] = Status.values();
        for (Status s: allStatuses)
            System.out.println(s);
        System.out.println();

        Status st = Status.valueOf("IN_THE_WAY");
        return st;
    }

    public Order provideDeliveryTime(long orderId, int deliveryTime) {
        var order = orderRepository.getById(orderId);
        order.setDeliveryTimeInMinutes(deliveryTime);
        orderRepository.save(order);
        return order;
    }

    public void rateOrder(long orderId, Rating rating) {
        var rateOrder = orderRepository.getById(orderId);
        rateOrder.setRating(rating);
        orderRepository.save(rateOrder);
    }


    }


