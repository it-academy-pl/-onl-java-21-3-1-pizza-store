package itacademy.pizzastore;

import java.math.BigDecimal;

public class Ingredients {
    protected String name;
    protected BigDecimal price;

    public Ingredients(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
