package itacademy.pizzastore.web;

import itacademy.pizzastore.domain.Ingredient;
import itacademy.pizzastore.domain.Pizza;
import itacademy.pizzastore.domain.Size;
import itacademy.pizzastore.service.IngredientService;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService){
        this.ingredientService = ingredientService;
    }

    @GetMapping("/list")
    public List<IngredientResponse> getAvailableIngredients() {
        return ingredientService.availableIngredients().stream()
                .map(IngredientResponse::from)
                .collect(Collectors.toList());
    }

    @Value
    private static class IngredientResponse {
        long id;
        String name;
        BigDecimal price;

        public static IngredientResponse from(Ingredient ingredient) {
            return new IngredientResponse(
                    ingredient.getId(),
                    ingredient.getName(),
                    ingredient.getPrice()
            );
        }
    }
}
