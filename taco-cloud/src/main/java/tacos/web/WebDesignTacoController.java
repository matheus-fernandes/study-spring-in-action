package tacos.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.entity.Ingredient;
import tacos.entity.Taco;
import tacos.entity.Order;
import tacos.entity.User;
import tacos.repository.IngredientRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static tacos.entity.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@RequiredArgsConstructor
public class WebDesignTacoController {
    private final IngredientRepository ingredientRepository;
    @ModelAttribute
    public void addIngredientsToModel(Model model, @AuthenticationPrincipal User user){
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        Type[] types = Type.values();
        for (Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute Order order){
        if (errors.hasErrors()){
            return "design";
        }

        order.addTaco(taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type){
        List<Ingredient> ingredientList = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getType().equals(type)){
                ingredientList.add(ingredient);
            }
        }

        return ingredientList;
    }
}
