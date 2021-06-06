package itacademy.pizzastore.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Status {
    String newOrder;
    String payed;
    String baked;
    String inTheWay;
    int deliveryTimeInMinutes;

}
