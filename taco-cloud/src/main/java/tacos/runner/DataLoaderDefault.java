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
            ingredientRepository.save(getFlourTortilla());
            ingredientRepository.save(getCornTortilla());
            ingredientRepository.save(getIntegralTortilla());
            ingredientRepository.save(getGroundBeef());
            ingredientRepository.save(getCarnitas());
            ingredientRepository.save(getDicedTomatoes());
            ingredientRepository.save(getLettuce());
            ingredientRepository.save(getCheddar());
            ingredientRepository.save(getMonterreyJack());
            ingredientRepository.save(getSalsa());
            ingredientRepository.save(getSourCream());

            Taco taco1 = getTaco1();
            Taco taco2 = getTaco2();
            Taco taco3 = getTaco3();

            tacoRepository.save(taco1);
            tacoRepository.save(taco2);
            taco3 = tacoRepository.save(taco3);

            User user = getUser(passwordEncoder);
            user = userRepository.save(user);


            Order order = getOrder(tacoRepository.findById(taco3.getId()).get(), user);
            orderRepository.save(order);
        };
    }

    public Order getOrder(Taco taco, User user) {
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
        order.addTaco(taco);
        return order;
    }

    public User getUser(PasswordEncoder passwordEncoder) {
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
        return user;
    }

    public Taco getTaco3() {
        Taco taco3 = new Taco();
        taco3.setName("Veg-Out");
        taco3.setIngredients(Arrays.asList(
                getFlourTortilla(), getCornTortilla(), getDicedTomatoes(), getLettuce(), getSalsa()));
        return taco3;
    }

    public Taco getTaco2() {
        Taco taco2 = new Taco();
        taco2.setName("Bovine Bountry");
        taco2.setIngredients(Arrays.asList(
                getCornTortilla(), getGroundBeef(), getCheddar(), getMonterreyJack(), getSourCream()));
        return taco2;
    }

    public Taco getTaco1() {
        Taco taco1 = new Taco();
        taco1.setName("Carnivore");
        taco1.setIngredients(Arrays.asList(
                getFlourTortilla(), getGroundBeef(), getCarnitas(), getSourCream(), getSalsa(), getCheddar()));
        return taco1;
    }

    public Ingredient getSourCream() {
        return new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
    }

    public Ingredient getSalsa() {
        return new Ingredient("SLSA", "Salsa", Type.SAUCE);
    }

    public Ingredient getMonterreyJack() {
        return new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
    }

    public Ingredient getCheddar() {
        return new Ingredient("CHED", "Cheddar", Type.CHEESE);
    }

    public Ingredient getLettuce() {
        return new Ingredient("LETC", "Lettuce", Type.VEGGIE);
    }

    public Ingredient getDicedTomatoes() {
        return new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIE);
    }

    public Ingredient getCarnitas() {
        return new Ingredient("CARN", "Carnitas", Type.PROTEIN);
    }

    public Ingredient getGroundBeef() {
        return new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
    }

    public Ingredient getIntegralTortilla() {
        return new Ingredient("INTO", "Integral Tortilla", Type.WRAP);
    }

    public Ingredient getCornTortilla() {
        return new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
    }

    public Ingredient getFlourTortilla() {
        return new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
    }
}
