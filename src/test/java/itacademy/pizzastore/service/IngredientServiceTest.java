package itacademy.pizzastore.service;

import itacademy.pizzastore.config.ApplicationConfiguration;
import itacademy.pizzastore.domain.Ingredient;
import itacademy.pizzastore.domain.Pizza;
import itacademy.pizzastore.domain.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IngredientServiceTest {
    private IngredientService ingredientService;
    private ApplicationConfiguration configuration;
    private PizzaService pizzaService;

    @BeforeEach
    public void setUp() {
        configuration = new ApplicationConfiguration();
        ingredientService = new IngredientService(configuration);
        pizzaService = new PizzaService(configuration, ingredientService);

    }

    @Test
    public void fromIds_ReturnIngredientsById() {
        Pizza mediumAmericana = new Pizza(5, Size.MEDIUM, "Medium Americana", BigDecimal.TEN, List.of("Mozzarella cheese", "Sauce"), List.of());
        Map<Long, Pizza> pizzas = Map.of(5L, mediumAmericana);

        configuration.setPizzas(pizzas);

        List<Ingredient> result = ingredientService.fromIds(List.of(5L));
        assertThat(result).contains();
    }

}