package tacos;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import tacos.constant.ProfileConstants;
import tacos.entity.Ingredient;
import tacos.repository.IngredientRepository;
import tacos.runner.DataLoaderDefault;

import java.util.Optional;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles(ProfileConstants.DEFAULT)
class IngredientDataRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("/data-api/ingredients")
    private String url;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private DataLoaderDefault dataLoaderDefault;

    @Order(1)
    @Test
    void testGet(){
        Ingredient salsa = dataLoaderDefault.getSalsa();
        Ingredient respIngredient = restTemplate.getForObject(url + "/{id}", Ingredient.class, salsa.getId());

        Assertions.assertEquals(salsa.getName(), respIngredient.getName());
    }

    @Order(2)
    @Test
    void testPut(){
        Ingredient salsa = dataLoaderDefault.getSalsa();
        Ingredient newSalsa = new Ingredient(salsa.getId(), "New Salsa", salsa.getType());
        restTemplate.put(url + "/{id}", newSalsa, newSalsa.getId());
        Ingredient repoIngredient = ingredientRepository.findById(newSalsa.getId()).orElseThrow();

        Assertions.assertNotEquals(salsa.getName(), repoIngredient.getName());
        Assertions.assertEquals(newSalsa.getName(), repoIngredient.getName());
    }

    @Order(3)
    @Test
    void testPost(){
        Ingredient pepper = new Ingredient("PPPR", "Pepper", Ingredient.Type.SAUCE);
        restTemplate.postForObject(url + "/", pepper, Ingredient.class);

        Ingredient repoIngredient = ingredientRepository.findById(pepper.getId()).orElseThrow();
        Assertions.assertEquals(pepper.getName(), repoIngredient.getName());
    }

    @Order(4)
    @Test
    void testDelete(){
        String id = "PPPR";
        if (ingredientRepository.findById(id).isEmpty()){
            ingredientRepository.save(new Ingredient(id, "Pepper", Ingredient.Type.SAUCE));
        }
        Optional<Ingredient> addedIngredient = ingredientRepository.findById(id);
        Assertions.assertTrue(addedIngredient.isPresent());

        restTemplate.delete(url + "/{id}", id);

        Optional<Ingredient> emptyIngredient = ingredientRepository.findById(id);
        Assertions.assertTrue(emptyIngredient.isEmpty());
    }


}
