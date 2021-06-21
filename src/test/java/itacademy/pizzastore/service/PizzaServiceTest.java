package itacademy.pizzastore.service;

import itacademy.pizzastore.config.ApplicationConfiguration;
import itacademy.pizzastore.domain.Pizza;
import itacademy.pizzastore.domain.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PizzaServiceTest {
    private PizzaService pizzaService;
    private ApplicationConfiguration configuration;
    private IngredientService ingredientService;

    @BeforeEach
    public void setUp() {
        configuration = new ApplicationConfiguration();
        ingredientService = new IngredientService(configuration);
        pizzaService = new PizzaService(configuration, ingredientService);

    }

    @Test
    public void fromIds_ReturnsPizzaById() {
        Pizza smallAmericana = new Pizza(4, Size.SMALL, "Small Americana", BigDecimal.TEN, List.of("Mozzarella", "Dough"), List.of());
        Map<Long, Pizza> pizzas = Map.of(4L, smallAmericana);

        configuration.setPizzas(pizzas);

        List<Pizza> result = pizzaService.fromIds(List.of(4L));
        assertThat(result).contains(smallAmericana);
    }

}