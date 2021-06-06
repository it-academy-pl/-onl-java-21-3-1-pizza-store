package itacademy.pizzastore.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PizzaNameList {
    ArrayList<String> pizzaNames = new ArrayList<String>();

    public ArrayList<String> getPizzaNames() {
        return pizzaNames;
    }
}
