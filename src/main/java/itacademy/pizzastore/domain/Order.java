package itacademy.pizzastore.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class Order {

    String id;
    List<Pizza> pizzas;
    Address deliveryAddress;
    String phoneNumber;
    BigDecimal totalPrice;
    Status status;
    int deliveryTimeInMinutes;
    PaymentType paymentType;
}
