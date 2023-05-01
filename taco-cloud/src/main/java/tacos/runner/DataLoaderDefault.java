package tacos.runner;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.constant.ProfileConstants;
import tacos.entity.Ingredient;
import tacos.entity.Ingredient.Type;
import tacos.entity.Order;
import tacos.entity.Taco;
import tacos.entity.User;
import tacos.repository.IngredientRepository;
import tacos.repository.OrderRepository;
import tacos.repository.TacoRepository;
import tacos.repository.UserRepository;

import java.util.Arrays;
import java.util.Date;


@Profile(ProfileConstants.DEFAULT)
@Configuration
public class DataLoaderDefault {
    @Bean
    public ApplicationRunner dataLoaderRunner(
            IngredientRepository ingredientRepository,
            UserRepository userRepository,
            TacoRepository tacoRepository,
            OrderRepository orderRepository,
            PasswordEncoder passwordEncoder){
        return args -> {
            Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
            Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
            Ingredient integralTortilla = new Ingredient("INTO", "Integral Tortilla", Type.WRAP);
            Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
            Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
            Ingredient dicedTomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIE);
            Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIE);
            Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
            Ingredient monterreyJack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
            Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
            Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);

            ingredientRepository.save(flourTortilla);
            ingredientRepository.save(cornTortilla);
            ingredientRepository.save(integralTortilla);
            ingredientRepository.save(groundBeef);
            ingredientRepository.save(carnitas);
            ingredientRepository.save(dicedTomatoes);
            ingredientRepository.save(lettuce);
            ingredientRepository.save(cheddar);
            ingredientRepository.save(monterreyJack);
            ingredientRepository.save(salsa);
            ingredientRepository.save(sourCream);

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));

            Taco taco2 = new Taco();
            taco2.setName("Bovine Bountry");
            taco2.setIngredients(Arrays.asList(
                    cornTortilla, groundBeef, cheddar, monterreyJack, sourCream));

            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(
                    flourTortilla, cornTortilla, dicedTomatoes, lettuce, salsa));

            tacoRepository.save(taco1);
            tacoRepository.save(taco2);
            tacoRepository.save(taco3);

            User user = new User(
                    "user",
                    passwordEncoder.encode("123456"),
                    "User Default",
                    "Street",
                    "City",
                    "State",
                    "13054587",
                    "19999885588"
            );
            user = userRepository.save(user);

            Order order = new Order();
            order.setUser(user);
            order.setDeliveryName(user.getFullName());
            order.setDeliveryCity(user.getCity());
            order.setDeliveryState(user.getState());
            order.setDeliveryStreet(user.getStreet());
            order.setDeliveryZip(user.getZip());
            order.setPlacedAt(new Date());
            order.setCcCVV("333");
            order.setCcExpiration("01/27");
            order.setCcNumber("4391796524153766");
            order.addTaco(tacoRepository.findById(taco3.getId()).get());

            orderRepository.save(order);
        };
    }
}
