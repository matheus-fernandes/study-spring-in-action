package tacos.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.model.Ingredient;

import java.util.Map;

@Component
public class IngredientsByIdConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientMap;

    public IngredientsByIdConverter(){
        this.ingredientMap = Map.of(
                "FLTO", new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                "COTO", new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                "GRBF", new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                "CARN", new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                "TMTO", new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIE),
                "LETC", new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIE),
                "CHED", new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                "JACK", new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                "SLSA", new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                "SRCR", new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
