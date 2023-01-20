package tacos;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import tacos.repository.IngredientRepository;
import tacos.repository.OrderRepository;

@TestConfiguration
public class WebMvcConfiguration {
    @MockBean
    IngredientRepository ingredientRepository;

    @MockBean
    OrderRepository orderRepository;

    @Bean
    OrderRepository getOrderRepository(){
        return orderRepository;
    }

    @Bean
    IngredientRepository getIngredientRepository(){
        return ingredientRepository;
    }
}
