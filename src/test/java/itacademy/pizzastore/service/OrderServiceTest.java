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

        Order location = orderService.provideDeliveryAddress(11, null);
        assertThat(location.getDeliveryAddress()).isEqualTo(null);
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

        Order rank = orderService.rateOrder(332, null);
        assertThat(rank.getRating()).isEqualTo(null);
    }


}