package tacos.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.model.Ingredient;
import tacos.model.IngredientRef;
import tacos.repository.IngredientRepository;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class IngredientsByIdConverter implements Converter<String, IngredientRef> {

    private final IngredientRepository ingredientRepository;

    @Override
    public IngredientRef convert(String id) {
        return ingredientRepository.findById(id).map(Ingredient::getId).map(IngredientRef::new).orElse(null);
    }
}
