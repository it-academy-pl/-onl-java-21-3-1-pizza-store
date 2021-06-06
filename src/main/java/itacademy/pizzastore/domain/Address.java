package itacademy.pizzastore.domain;

import lombok.Value;

@Value
public class Address {
    String city;
    String street;
    String houseNumber;
    String flatNumber;
    String postCode;
    String mobilephone;
}
