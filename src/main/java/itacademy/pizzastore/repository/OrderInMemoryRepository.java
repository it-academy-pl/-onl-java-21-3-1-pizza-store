package itacademy.pizzastore.repository;

import itacademy.pizzastore.domain.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class OrderInMemoryRepository implements OrderRepository {
    private final HashMap<Long, Order> orders = new HashMap<>();

    @Override
    public Order getById(long id) {
        return orders.get(id);
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }
}
