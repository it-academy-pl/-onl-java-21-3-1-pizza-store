package itacademy.pizzastore.service;

import itacademy.pizzastore.config.ApplicationConfiguration;
import itacademy.pizzastore.domain.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final ApplicationConfiguration configuration;

    public Collection<Ingredient> availableIngredients() {
        return configuration.getAdditionalIngredients().values();
    }

    public Ingredient typesIngredient() {
        return null;
    }
}
