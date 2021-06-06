package itacademy.pizzastore.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Ingredient {

    String name;
    BigDecimal price;
}
