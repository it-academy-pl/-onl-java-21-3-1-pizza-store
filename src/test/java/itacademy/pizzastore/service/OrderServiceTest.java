package itacademy.pizzastore.service;

import itacademy.pizzastore.domain.*;
import itacademy.pizzastore.repository.OrderInMemoryRepository;
import itacademy.pizzastore.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = new OrderInMemoryRepository();
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void createGetAllPricesFromPizzasToOrder() {
        Pizza one = new Pizza(1, Size.LARGE, "1", BigDecimal.TEN, List.of(), List.of());
        Pizza two = new Pizza(1, Size.LARGE, "2", BigDecimal.ONE, List.of(), List.of());

        Order result = orderService.create(List.of(one, two));

        assertThat(result.getTotalPrice()).isEqualTo(BigDecimal.valueOf(11));
    }

    @Test
    public void getStatusForOrderReturnsOrderStatus() {
        Pizza one = new Pizza(1, Size.LARGE, "1", BigDecimal.TEN, List.of(), List.of());
        Order order = new Order(123, List.of(one), null, "123-456-789", BigDecimal.TEN, Status.PAYED, 30, PaymentType.CASH, null);
        orderRepository.save(order);

        Status result = orderService.getStatusForOrder(123);
        assertThat(result).isEqualTo(Status.PAYED);
    }
}