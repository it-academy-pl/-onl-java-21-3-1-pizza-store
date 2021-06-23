package itacademy.pizzastore.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import itacademy.pizzastore.domain.*;
import itacademy.pizzastore.service.OrderService;
import itacademy.pizzastore.service.PizzaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private PizzaService pizzaService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createOrder_provideEmptyList_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/order")
                .content("[]")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createOrder_provideListWithIDs_returnsCreatedStatusWithOrder() throws Exception {
        List<Long> ids = List.of(2L, 3L, 5L);

        Pizza smallAmericana = new Pizza(2, Size.SMALL, "Small Americana", BigDecimal.TEN, List.of("Mozzarella", "Dough"), List.of());
        Pizza mediumMargarita = new Pizza(3, Size.MEDIUM, "Medium Margarita", BigDecimal.valueOf(12), List.of("Mozzarella", "Dough"), List.of());
        Pizza largeFourCheese = new Pizza(5, Size.LARGE, "Large 4 Cheese", BigDecimal.valueOf(15), List.of("Mozzarella", "Parmezano", "Grana Padana", "Dough"), List.of());
        List<Pizza> pizzas = List.of(smallAmericana, mediumMargarita, largeFourCheese);

        when(pizzaService.fromIds(ids)).thenReturn(pizzas);

        Order order = new Order(1, pizzas, null, "223-456-789", BigDecimal.valueOf(37), Status.NEW, 30, PaymentType.CARD, null);
        when(orderService.create(pizzas)).thenReturn(order);

        String expectedJson = objectMapper.writeValueAsString(OrderController.OrderResponse.from(order));

        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[2, 3, 5]"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }
}