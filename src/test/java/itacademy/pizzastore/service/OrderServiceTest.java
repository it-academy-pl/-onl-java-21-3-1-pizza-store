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
    public void getPaymentTypeReturnPaymentType() {
        Pizza two = new Pizza(1, Size.LARGE, "2", BigDecimal.ONE, List.of(), List.of());
        Order order = new Order(124, List.of(two), null, "223-456-789", BigDecimal.ONE, Status.PAYED, 25, PaymentType.CARD);
        OrderRepository.save(order);

        order result = orderService.getStatusForOrder(124, PaymentType.CARD);
        assertThat(result).isEqualTo(PaymentType.CARD);
    }
  
    @Test
    public void getStatusForOrderReturnsOrderStatus() {
        Pizza one = new Pizza(1, Size.LARGE, "1", BigDecimal.TEN, List.of(), List.of());
        Order order = new Order(123, List.of(one), null, "123-456-789", BigDecimal.TEN, Status.PAYED, 30, PaymentType.CASH, null);
        orderRepository.save(order);

        Status result = orderService.getStatusForOrder(123);
        assertThat(result).isEqualTo(Status.PAYED);
    }

    @Test
    public void checkPaymentType(){
        Order order = new Order(123, List.of(), null, "123-456-789", BigDecimal.TEN, Status.PAYED, 30, null, null);
        orderRepository.save(order);
        orderService.choosePaymentType(123, PaymentType.CARD);
        PaymentType result = orderRepository.getById(123).getPaymentType();
        assertThat(result).isEqualTo(PaymentType.CARD);
    }

    @Test
    public void getDeliveryTimeInMinutes() {
        Pizza two = new Pizza(1, Size.LARGE, "2", BigDecimal.ONE, List.of(), List.of());
        Order order = new Order(124, List.of(two), null, "223-456-789", BigDecimal.ONE, Status.PAYED, 25, PaymentType.CARD);
        OrderRepository.save(order);

        Order result = orderService.getStatusForOrder(124, getDeliveryTimeInMinutes(25));
        assertThat(result).isEqualTo(25);
    }

    @Test
    public void cancelOrder(){
        Order order = new Order(123, List.of(), null, "123-456-789", BigDecimal.TEN, Status.NEW, 30, null, null);
        orderRepository.save(order);
        orderService.cancel(123);
        Status result = orderRepository.getById(123).getStatus();

        assertThat(result).isEqualTo(Status.ANULLED);
    }

    @Test
    public void checkDeliveryTime(){
        Order order = new Order(123, List.of(), null, "123-456-789", BigDecimal.TEN, Status.NEW, 30, null, null);
        orderRepository.save(order);
        orderService.provideDeliveryTime(123, 50);
        int result = orderRepository.getById(123).getDeliveryTimeInMinutes();

        assertThat(result).isEqualTo(50);
    }

    @Test
    public void checkRate(){
        Order order = new Order(123, List.of(), null, "123-456-789", BigDecimal.TEN, Status.NEW, 30, null, null);
        orderRepository.save(order);
        Rating rating = new Rating(5, "very good");
        orderService.rateOrder(123,rating);
        Rating result = orderRepository.getById(123).getRating();

        assertThat(result).isEqualTo(rating);
    }
}
