package itacademy.pizzastore.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class Pizza {
    long id;
    Size size;
    String name;
    BigDecimal price;
    List<String> ingredients;
    List<Ingredient> additionalIngredients;
}
