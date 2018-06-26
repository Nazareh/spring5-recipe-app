package turmina.nazareh.spring5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import turmina.nazareh.spring5recipeapp.services.RecipeService;

@Slf4j
@Controller
public class RecipeController {
    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipes")
    public String getRecipes(Model model){
        log.debug("Getting recipes");
        model.addAttribute("recipes",recipeService.getRecipes());

        return "recipes";
    }
}
