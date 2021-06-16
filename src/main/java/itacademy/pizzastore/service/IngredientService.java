package itacademy.pizzastore.service;

import itacademy.pizzastore.config.ApplicationConfiguration;
import itacademy.pizzastore.domain.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final ApplicationConfiguration configuration;

    public Collection<Ingredient> availableIngredients() {
        return configuration.getAdditionalIngredients().values();
    }

    //TODO: implement the method
    public List<Ingredient> fromIds(List<Long> ids) {
        return List.of();
    }

    public Ingredient typesIngredient() {
        return null;
    }
}
