package itacademy.pizzastore.service;

import itacademy.pizzastore.config.ApplicationConfiguration;
import itacademy.pizzastore.domain.Pizza;
import itacademy.pizzastore.domain.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final ApplicationConfiguration configuration;
    private final IngredientService ingredientService;

    public Collection<Pizza> availablePizzas() {
        return configuration.getPizzas().values();
    }

    //TODO: implement the method
    public Pizza customPizza(List<Long> ingredientIds, Size size) {
        return null;
    }

    //TODO: for non existing IDs throw an PizzaNotFoundException
    //TODO: add the test for this case
    public List<Pizza> fromIds(List<Long> ids) {
        return ids.stream()
                .map(id -> configuration.getPizzas().get(id))
                .collect(Collectors.toList());
    }

}
