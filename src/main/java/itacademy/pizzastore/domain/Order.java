package itacademy.pizzastore.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Order {
    private static long lastId = 0;
    private long id;
    private List<Pizza> pizzas;
    private Address deliveryAddress;
    private String phoneNumber;
    private BigDecimal totalPrice;
    private Status status;
    private int deliveryTimeInMinutes;
    private PaymentType paymentType;
    private Rating rating;

    public Order(List<Pizza> pizzas, Address deliveryAddress, String phoneNumber, BigDecimal totalPrice, Status status, int deliveryTimeInMinutes, PaymentType paymentType, Rating rating) {
        this(++lastId, pizzas, deliveryAddress, phoneNumber, totalPrice, status, deliveryTimeInMinutes, paymentType, rating);
    }
}
