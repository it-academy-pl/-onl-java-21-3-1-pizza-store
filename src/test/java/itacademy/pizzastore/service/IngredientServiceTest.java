package itacademy.pizzastore.service;

import itacademy.pizzastore.config.ApplicationConfiguration;
import itacademy.pizzastore.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class IngredientServiceTest {
    private IngredientService ingredientService;
    private ApplicationConfiguration configuration;

    @BeforeEach
    public void setUp() {
        configuration = new ApplicationConfiguration();
        ingredientService = new IngredientService(configuration);
    }

    @Test
    public void fromIds_ReturnIngredientsById() {
        Ingredient cheese = new Ingredient(5L, "Cheese", BigDecimal.valueOf(2));
        Map<Long, Ingredient> ingredients = Map.of(5L, cheese);

        configuration.setAdditionalIngredients(ingredients);

        List<Ingredient> result = ingredientService.fromIds(List.of(5L));
        assertThat(result).contains(cheese);
    }

}