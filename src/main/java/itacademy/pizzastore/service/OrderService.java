package itacademy.pizzastore.service;

import itacademy.pizzastore.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    public Order create(List<Pizza> pizzas) {
        return null;
    }

    public Order provideDeliveryAddress(long orderId, Address deliveryAddress) {
        return null;
    }

    public Status cancel(long orderId) {
        return null;
    }

    public Status getStatusForOrder(long orderId) {
        return null;
    }

    public void rateOrder(long orderId, Rating rating) {

    }

}
