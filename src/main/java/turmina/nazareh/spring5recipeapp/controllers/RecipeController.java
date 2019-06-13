package turmina.nazareh.spring5recipeapp.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import turmina.nazareh.spring5recipeapp.commands.RecipeCommand;
import turmina.nazareh.spring5recipeapp.domain.Recipe;
import turmina.nazareh.spring5recipeapp.services.RecipeService;

@Slf4j
@Controller
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showByID(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand,Model model ){
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/" + savedRecipeCommand.getId() +"/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteAction(@PathVariable String id){
        log.debug("Deleting recipe id: "+ id);

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
