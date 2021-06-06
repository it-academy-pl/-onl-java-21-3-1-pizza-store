package itacademy.pizzastore.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AdditionalIngredients {
    private ArrayList<String> ingredients = new ArrayList<String>();

    public ArrayList<String> getIngredients() {
        return ingredients;
    }
}
