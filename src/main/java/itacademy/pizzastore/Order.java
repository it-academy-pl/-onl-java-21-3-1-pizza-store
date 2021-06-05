package itacademy.pizzastore;

import itacademy.pizzastore.domain.Address;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    protected int id;
    private static int i = 1;
    protected List<Pizza> pizzas;
    protected Address deliveryAdress;
    protected String phoneNumber;
    protected BigDecimal totalPrice;
    protected Status status;
    protected int deliveryTimeInMinutes;
    protected PaymentType paymentType;

    public Order(List<Pizza> pizzas, Address deliveryAdress, String phoneNumber, BigDecimal totalPrice, Status status, int deliveryTimeInMinutes, PaymentType paymentType) {
        this.id = i;
        this.pizzas = pizzas;
        this.deliveryAdress = deliveryAdress;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
        this.status = status;
        this.deliveryTimeInMinutes = deliveryTimeInMinutes;
        this.paymentType = paymentType;
        i++;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public Address getDeliveryAdress() {
        return deliveryAdress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public int getDeliveryTimeInMinutes() {
        return deliveryTimeInMinutes;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
}
