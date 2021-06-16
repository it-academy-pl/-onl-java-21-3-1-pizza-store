package itacademy.pizzastore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    private long id;
    private Size size;
    private String name;
    private BigDecimal price;
    private List<String> ingredients;
    private List<Ingredient> additionalIngredients;
}
