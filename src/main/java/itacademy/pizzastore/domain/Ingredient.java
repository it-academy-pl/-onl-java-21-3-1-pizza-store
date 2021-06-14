package itacademy.pizzastore.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Ingredient {
    private long id;
    private String name;
    private BigDecimal price;
}
