package itacademy.pizzastore.service;

import itacademy.pizzastore.domain.Ingredient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientService {
    public List<Ingredient> availableIngredients() {
        return List.of();
    }

    public Ingredient typesIngredient(){
        return null;
    }

    private Ingredient meat(){
        return new Ingredient(11, "Bacon", BigDecimal.valueOf(5));
    }

    private Ingredient vegetable(){
        return new Ingredient(21, "Corn", BigDecimal.valueOf(2));
    }

    private Ingredient dairy(){
        return new Ingredient(31, "Gorgonzola", BigDecimal.valueOf(3));
    }
}
