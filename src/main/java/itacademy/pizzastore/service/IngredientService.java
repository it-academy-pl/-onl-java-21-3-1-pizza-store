package itacademy.pizzastore.service;

import itacademy.pizzastore.domain.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    public List<Ingredient> availableIngredients() {
        return List.of();
    }
}
