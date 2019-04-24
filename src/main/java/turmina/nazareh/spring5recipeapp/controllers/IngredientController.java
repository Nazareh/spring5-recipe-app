package turmina.nazareh.spring5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import turmina.nazareh.spring5recipeapp.services.RecipeService;

@Slf4j
@Controller
public class IngredientController {
    private RecipeService recipeService;

    @Autowired
    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String showIngredients(@PathVariable String id, Model model){

        log.debug("Getting ingredient list for recipe id:" + id);

        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));

        return "/recipe/ingredient/list";
    }
}
