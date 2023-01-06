package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.model.Ingredient;
import tacos.model.Taco;
import tacos.model.TacoOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static tacos.model.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Flour Tortilla", Type.PROTEIN),
                new Ingredient("CARN", "Flour Tortilla", Type.PROTEIN),
                new Ingredient("TMTO", "Flour Tortilla", Type.VEGGIE),
                new Ingredient("LETC", "Flour Tortilla", Type.VEGGIE),
                new Ingredient("CHED", "Flour Tortilla", Type.CHEESE),
                new Ingredient("JACK", "Flour Tortilla", Type.CHEESE),
                new Ingredient("SLSA", "Flour Tortilla", Type.SAUCE),
                new Ingredient("SRCR", "Flour Tortilla", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
