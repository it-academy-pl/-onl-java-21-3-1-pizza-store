package itacademy.pizzastore.service;

import itacademy.pizzastore.config.ApplicationConfiguration;
import itacademy.pizzastore.domain.Ingredient;
import itacademy.pizzastore.domain.Pizza;
import itacademy.pizzastore.domain.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final ApplicationConfiguration configuration;

    public Collection<Pizza> availablePizzas() {
        return configuration.getPizzas().values();
    }

    public Pizza customPizza(List<Ingredient> ingredients, Size size) {
        return null;
    }
}
