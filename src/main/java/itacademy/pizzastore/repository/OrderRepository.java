package itacademy.pizzastore.repository;

import itacademy.pizzastore.domain.Order;

import java.util.List;

public interface OrderRepository {
    Order getById(long id);

    List<Order> findAll();

    void save(Order order);
}
