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
    public void provideDeliveryAddressReturnsRealAddress() {
        Order address = new Order(11, List.of(), null, "123-456-789", BigDecimal.TEN, Status.PAYED, 30, PaymentType.CASH, null);
        orderRepository.save(address);

        Address deliveryAddress = new Address("Katowice", "Independence", "21", "12", "11-221");
        Order location = orderService.provideDeliveryAddress(11, deliveryAddress);
        assertThat(location.getDeliveryAddress()).isEqualTo(deliveryAddress);
    }

    @Test
    public void choosePaymentTypeReturnsActualType() {
        Order payType = new Order(120, List.of(), null, "123-456-789", BigDecimal.TEN, Status.PAYED, 30, PaymentType.CASH, null);
        orderRepository.save(payType);

        Order pay = orderService.choosePaymentType(120, PaymentType.CASH);
        assertThat(pay.getPaymentType()).isEqualTo(PaymentType.CASH);
    }

    @Test
    public void cancelTheOrder() {
        Order cancellation = new Order(60, List.of(), null, "123-456-789", BigDecimal.TEN, Status.CANCELLED, 30, PaymentType.CASH, null);
        orderRepository.save(cancellation);

        Status cancelOrder = orderService.cancel(60);
        assertThat(cancelOrder).isEqualTo(Status.CANCELLED);
    }

    @Test
    public void getPaymentTypeReturnPaymentType() {
        Pizza two = new Pizza(1, Size.LARGE, "2", BigDecimal.ONE, List.of(), List.of());
        Order order = new Order(124, List.of(two), null, "223-456-789", BigDecimal.ONE, Status.PAYED, 25, PaymentType.CARD, null);
        orderRepository.save(order);

        Status result = orderService.getStatusForOrder(124);
        assertThat(result).isEqualTo(Status.PAYED);
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
    public void provideDeliveryTimeReturnsInputtedValue() {
        Order orderTime = new Order(332, List.of(), null, "333-444-555", BigDecimal.TEN, Status.NEW, 35, PaymentType.CASH, null);
        Order orderTime2 = new Order(333, List.of(), null, "333-444-555", BigDecimal.TEN, Status.NEW, 40, PaymentType.CASH, null);
        orderRepository.save(orderTime);
        orderRepository.save(orderTime2);

        Order time = orderService.provideDeliveryTime(332, 35);
        Order time2 = orderService.provideDeliveryTime(333, 40);

        assertThat(time.getDeliveryTimeInMinutes()).isEqualTo(35);
        assertThat(time2.getDeliveryTimeInMinutes()).isEqualTo(40);
    }

    @Test
    public void rateOrderReturnMark() {
        Order order = new Order(332, List.of(), null, "333-444-555", BigDecimal.TEN, Status.NEW, 35, PaymentType.CASH, null);
        orderRepository.save(order);

        Rating rating = new Rating(5, "The best pizza I ever eaten before!");
        Order rank = orderService.rateOrder(332, rating);
        assertThat(rank.getRating()).isEqualTo(rating);
    }


    @Test
    public void checkPaymentType() {
        Order order = new Order(123, List.of(), null, "123-456-789", BigDecimal.TEN, Status.PAYED, 30, null, null);
        orderRepository.save(order);
        orderService.choosePaymentType(123, PaymentType.CARD);
        PaymentType result = orderRepository.getById(123).getPaymentType();
        assertThat(result).isEqualTo(PaymentType.CARD);
    }

    @Test
    public void getDeliveryTimeInMinutes() {
        Pizza two = new Pizza(1, Size.LARGE, "2", BigDecimal.ONE, List.of(), List.of());
        Order order = new Order(124, List.of(two), null, "223-456-789", BigDecimal.ONE, Status.PAYED, 25, PaymentType.CARD, null);
        orderRepository.save(order);

        Order result = orderService.provideDeliveryTime(124, 25);
        assertThat(result.getDeliveryTimeInMinutes()).isEqualTo(25);
    }

    @Test
    public void cancelOrder() {
        Order order = new Order(123, List.of(), null, "123-456-789", BigDecimal.TEN, Status.NEW, 30, null, null);
        orderRepository.save(order);
        orderService.cancel(123);
        Status result = orderRepository.getById(123).getStatus();

        assertThat(result).isEqualTo(Status.CANCELLED);
    }

    @Test
    public void checkDeliveryTime() {
        Order order = new Order(123, List.of(), null, "123-456-789", BigDecimal.TEN, Status.NEW, 30, null, null);
        orderRepository.save(order);
        orderService.provideDeliveryTime(123, 50);
        int result = orderRepository.getById(123).getDeliveryTimeInMinutes();

        assertThat(result).isEqualTo(50);
    }

    @Test
    public void checkRate() {
        Order order = new Order(123, List.of(), null, "123-456-789", BigDecimal.TEN, Status.NEW, 30, null, null);
        orderRepository.save(order);
        Rating rating = new Rating(5, "very good");
        orderService.rateOrder(123, rating);
        Rating result = orderRepository.getById(123).getRating();

        assertThat(result).isEqualTo(rating);
    }
}
