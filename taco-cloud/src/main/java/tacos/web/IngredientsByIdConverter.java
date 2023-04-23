package tacos.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.model.Ingredient;
import tacos.model.IngredientUDT;
import tacos.repository.IngredientRepository;

@Component
@RequiredArgsConstructor
public class IngredientsByIdConverter implements Converter<String, IngredientUDT> {

    private final IngredientRepository ingredientRepository;

    @Override
    public IngredientUDT convert(String id) {
        return ingredientRepository.findById(id)
                .map(i -> new IngredientUDT(i.getId().toString(), i.getName(), i.getType()))
                .orElse(null);
    }
}
