package itacademy.pizzastore.config;

import itacademy.pizzastore.domain.Ingredient;
import itacademy.pizzastore.domain.Pizza;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class ApplicationConfiguration {
    private Map<Long, Pizza> pizzas;
    private Map<Long, Ingredient> additionalIngredients;
    private String pizzaStoreName;
}
