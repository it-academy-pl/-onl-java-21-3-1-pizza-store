package itacademy.pizzastore;

import java.math.BigDecimal;
import java.util.List;

public class Pizza {
    protected int id;
    protected String name;
    protected Size size;
    protected BigDecimal price;
    protected List<Ingredients> ingredients;
    private static int i = 1;

    public Pizza(String name, Size size, BigDecimal price, List<Ingredients> ingredients) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.ingredients = ingredients;
        this.id = i;
        i++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }
}
