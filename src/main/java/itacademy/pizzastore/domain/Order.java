package itacademy.pizzastore.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@AllArgsConstructor
public class Order {
    private static long lastId = 0;
    long id;
    List<Pizza> pizzas;
    Address deliveryAddress;
    String phoneNumber;
    BigDecimal totalPrice;
    Status status;
    int deliveryTimeInMinutes;
    PaymentType paymentType;

    public Order(List<Pizza> pizzas, Address deliveryAddress, String phoneNumber, BigDecimal totalPrice, Status status, int deliveryTimeInMinutes, PaymentType paymentType) {
        this(++lastId, pizzas, deliveryAddress, phoneNumber, totalPrice, status, deliveryTimeInMinutes, paymentType);
    }
}
