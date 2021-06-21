package itacademy.pizzastore.service;

import itacademy.pizzastore.config.ApplicationConfiguration;
import itacademy.pizzastore.domain.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final ApplicationConfiguration configuration;

    public Collection<Ingredient> availableIngredients() {
        return configuration.getAdditionalIngredients().values();
    }

    public List<Ingredient> fromIds(List<Long> ids) {
        return ids.stream()
                .map(id -> configuration.getAdditionalIngredients().get(id))
                .collect(Collectors.toList());
    }

    public Ingredient typesIngredient() {
        return null;
    }
}
