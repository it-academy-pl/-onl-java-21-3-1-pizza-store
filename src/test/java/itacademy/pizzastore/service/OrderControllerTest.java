package itacademy.pizzastore.service;
import itacademy.pizzastore.domain.*;
import itacademy.pizzastore.repository.OrderInMemoryRepository;
import itacademy.pizzastore.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderControllerTest {
    private OrderService orderService;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = new OrderInMemoryRepository();
        orderService = new OrderService(orderRepository);
    }
    @Test
    public void get
}
