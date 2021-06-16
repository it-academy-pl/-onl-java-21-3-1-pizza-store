package itacademy.pizzastore.web;

import itacademy.pizzastore.domain.Ingredient;
import itacademy.pizzastore.domain.Pizza;
import itacademy.pizzastore.domain.Size;
import itacademy.pizzastore.service.PizzaService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @GetMapping("/list")
    public List<PizzaResponse> getAvailablePizzas() {
        return pizzaService.availablePizzas().stream()
                .map(PizzaResponse::from)
                .collect(Collectors.toList());
    }

    @Value
    private static class PizzaResponse {
        long id;
        Size size;
        String name;
        BigDecimal price;
        List<String> ingredients;
        List<Ingredient> additionalIngredients;

        public static PizzaResponse from(Pizza pizza) {
            return new PizzaResponse(
                    pizza.getId(),
                    pizza.getSize(),
                    pizza.getName(),
                    pizza.getPrice(),
                    pizza.getIngredients(),
                    pizza.getAdditionalIngredients()
            );
        }
    }
}
