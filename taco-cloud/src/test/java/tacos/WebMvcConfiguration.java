package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tacos.repository.IngredientRepository;
import tacos.repository.UserRepository;
import tacos.security.HttpSecurityDecorator;
import tacos.security.HttpSecurityDecoratorDefault;
import tacos.security.SecurityConfig;
import tacos.service.OrderService;
import tacos.service.TacoService;

@TestConfiguration
public class WebMvcConfiguration {
    @MockBean
    TacoService tacoService;

    @MockBean
    IngredientRepository ingredientRepository;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    OrderService orderService;

    @MockBean
    UserRepository userRepository;

    @Bean
    SecurityFilterChain getHttpSecurityConfig(HttpSecurity httpSecurity, HttpSecurityDecorator decorator) throws Exception {
        return new SecurityConfig().securityFilterChain(httpSecurity, decorator);
    }

    @Bean
    HttpSecurityDecorator getHttpSecurityDecorator(){
        return new HttpSecurityDecoratorDefault();
    }

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return passwordEncoder;
    }

    @Bean
    UserRepository getUserRepository(){
        return userRepository;
    }

    @Bean
    TacoService getTacoService(){
        return tacoService;
    }

    @Bean
    OrderService getOrderService(){
        return orderService;
    }

    @Bean
    IngredientRepository getIngredientRepository(){
        return ingredientRepository;
    }
}
