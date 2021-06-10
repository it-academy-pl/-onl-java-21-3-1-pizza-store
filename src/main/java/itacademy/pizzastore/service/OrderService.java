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
        return null;
    }

    public Order choosePaymentType(long orderId, PaymentType paymentType) {
        return null;
    }

    public Status cancel(long orderId) {
        return null;
    }

    public Status getStatusForOrder(long orderId) {
        return null;
    }

    public Order provideDeliveryTime(int deliveryTime) {
        return null;
    }

    public void rateOrder(long orderId, Rating rating) {

    }

}
