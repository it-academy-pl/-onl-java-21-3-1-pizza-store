package itacademy.pizzastore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    private long id;
    private String name;
    private BigDecimal price;
}
