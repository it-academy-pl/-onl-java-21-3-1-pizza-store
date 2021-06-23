package itacademy.pizzastore.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import itacademy.pizzastore.domain.Pizza;
import itacademy.pizzastore.domain.Size;
import itacademy.pizzastore.service.PizzaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PizzaController.class)
class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaService pizzaService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getAvailablePizzas_returnsListOfPizzasFromPizzaService() throws Exception {
        Pizza smallAmericana = new Pizza(1, Size.SMALL, "Small Americana", BigDecimal.TEN, List.of("Mozzarella", "Dough"), List.of());
        Pizza mediumMargarita = new Pizza(2, Size.MEDIUM, "Medium Margarita", BigDecimal.valueOf(12), List.of("Mozzarella", "Dough"), List.of());
        Pizza largeFourCheese = new Pizza(3, Size.LARGE, "Large 4 Cheese", BigDecimal.valueOf(15), List.of("Mozzarella", "Parmezano", "Grana Padana", "Dough"), List.of());
        List<Pizza> pizzas = List.of(smallAmericana, mediumMargarita, largeFourCheese);

        when(pizzaService.availablePizzas()).thenReturn(pizzas);

        String expectedJson = objectMapper.writeValueAsString(pizzas);

        mockMvc.perform(get("/pizzas/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

}