package itacademy.pizzastore.service;

import itacademy.pizzastore.domain.Ingredient;
import itacademy.pizzastore.domain.Pizza;
import itacademy.pizzastore.domain.Size;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PizzaService {
    public List<Pizza> availablePizzas() {
        return List.of(smallMargarita(), mediumMargarita(), largeMargarita());
    }

    public Pizza customPizza(List<Ingredient> ingredients, Size size) {
        return null;
    }

    private Pizza smallMargarita() {
        return new Pizza(1, Size.SMALL, "Margarita", BigDecimal.valueOf(25),
                List.of(new Ingredient(1, "Mozzarella cheese", BigDecimal.ZERO),
                        new Ingredient(2, "Souse", BigDecimal.ZERO),
                        new Ingredient(3, "Dough", BigDecimal.ZERO)));
    }

    private Pizza mediumMargarita() {
        return new Pizza(2, Size.MEDIUM, "Margarita", BigDecimal.valueOf(25),
                List.of(new Ingredient(1, "Mozzarella cheese", BigDecimal.ZERO),
                        new Ingredient(2, "Souse", BigDecimal.ZERO),
                        new Ingredient(3, "Dough", BigDecimal.ZERO)));
    }

    private Pizza largeMargarita() {
        return new Pizza(3, Size.LARGE, "Margarita", BigDecimal.valueOf(25),
                List.of(new Ingredient(1, "Mozzarella cheese", BigDecimal.ZERO),
                        new Ingredient(2, "Souse", BigDecimal.ZERO),
                        new Ingredient(3, "Dough", BigDecimal.ZERO)));
    }
}
