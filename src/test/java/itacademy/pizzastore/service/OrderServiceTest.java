package itacademy.pizzastore.service;

import itacademy.pizzastore.domain.Order;
import itacademy.pizzastore.domain.Pizza;
import itacademy.pizzastore.domain.Size;
import itacademy.pizzastore.repository.OrderInMemoryRepository;
import itacademy.pizzastore.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        OrderRepository orderRepository = new OrderInMemoryRepository();
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void createGetAllPricesFromPizzasToOrder() {
        Pizza one = new Pizza(1, Size.LARGE, "1", BigDecimal.TEN, List.of());
        Pizza two = new Pizza(1, Size.LARGE, "2", BigDecimal.ONE, List.of());

        Order result = orderService.create(List.of(one, two));

        assertThat(result.getTotalPrice()).isEqualTo(BigDecimal.valueOf(11));
    }

}